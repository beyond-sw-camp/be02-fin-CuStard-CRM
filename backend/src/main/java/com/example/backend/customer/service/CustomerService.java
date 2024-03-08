package com.example.backend.customer.service;




import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.model.request.PostCustomerLoginReq;
import com.example.backend.customer.model.request.PostCustomerSignupReq;
import com.example.backend.customer.model.response.DeleteCustomerDeleteRes;
import com.example.backend.customer.model.response.PostCustomerLoginRes;
import com.example.backend.customer.model.response.PostCustomerSignupRes;
import com.example.backend.customer.repository.CustomerRepository;
import com.example.backend.log.service.LoginLogService;
import com.example.backend.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerEmailVerifyService customerEmailVerifyService;
    private final TokenProvider tokenProvider;
    private final LoginLogService loginLogService;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.token.expired-time-ms}")
    private Integer expiredTimeMs;
    public Customer getMemberByEmail (String customerEmail){
        return customerRepository.findByCustomerEmail(customerEmail).get();
    }


    // Member CRUD

    // create
    public PostCustomerSignupRes signup(PostCustomerSignupReq postCustomerSignupReq){

        Optional<Customer> duplicatedMember = customerRepository.findByCustomerEmail(postCustomerSignupReq.getCustomerEmail());
        // 멤버 정보를 빌드로 저장
        if(!duplicatedMember.isPresent()) {

            Customer customer = Customer.builder()
                    .customerEmail(postCustomerSignupReq.getCustomerEmail())
                    .customerPwd(passwordEncoder.encode(postCustomerSignupReq.getCustomerPwd()))
                    .authority("CUSTOMER")
                    .status(false)
                    .build();

            customerRepository.save(customer);

            Customer customer1 = customerRepository.findByCustomerEmail(customer.getCustomerEmail()).get();

            customerEmailVerifyService.sendCustomerMail(customer1);
            Map<String, String> result = new HashMap<>();
            result.put("customerEmail", customer.getCustomerEmail());

            PostCustomerSignupRes postCustomerSignupRes = PostCustomerSignupRes.builder()
                    .isSuccess(true)
                    .code(1000L)
                    .message("회원가입 성공.")
                    .result(result)
                    .build();

            return postCustomerSignupRes;

        } else {

            PostCustomerSignupRes postCustomerSignupRes = PostCustomerSignupRes.builder()
                    .isSuccess(false)
                    .code(4000L)
                    .message("요청 실패. 중복된 이메일입니다.")
                    .result(null)
                    .build();

            return postCustomerSignupRes;

        }


    }
    public PostCustomerLoginRes customerLogin(PostCustomerLoginReq postCustomerLoginReq) {
        Optional<Customer> member = customerRepository.findByCustomerEmail(postCustomerLoginReq.getCustomerEmail());

        if (member.isPresent()) {
            if (passwordEncoder.matches(postCustomerLoginReq.getCustomerPwd(), member.get().getCustomerPwd())) {

                PostCustomerLoginRes postCustomerLoginRes = PostCustomerLoginRes.builder()
                        .accessToken(TokenProvider.generateAccessToken(member.get(), expiredTimeMs))
                        .idx(member.get().getIdx())
                        .build();

                loginLogService.loginLogging(member.get());
                return postCustomerLoginRes;
            } else {
                return null;
            }
        }
        return null;
    }

    public DeleteCustomerDeleteRes delete(String token){
        token = TokenProvider.replaceToken(token);

        Optional<Customer> result = customerRepository.findByCustomerEmail(TokenProvider.getUsername(token));

        if (result.isPresent()){
            DeleteCustomerDeleteRes deleteCustomerDeleteRes = DeleteCustomerDeleteRes.builder()
                    .customerEmail(result.get().getCustomerEmail())
                    .build();

            customerRepository.delete(Customer.builder()
                    .idx(TokenProvider.getIdx(token)).build());

            return deleteCustomerDeleteRes;
        }
        return null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<Customer> result = customerRepository.findByCustomerEmail(username);
        Customer customer = null;
        if(result.isPresent()) {
            customer = result.get();
        }
        return customer;
    }

    // read
    // 내 정보 조회
//    public GetCustomerReadRes readMember (String username) {
//        Optional<Customer> result = memberRepository.findByCustomerEmail(username);
//
//        Customer customer = result.get();
//
//        GetCustomerReadRes response = GetCustomerReadRes.builder()
//                .customerEmail(customer.getCustomerEmail())
//                .authority(customer.getAuthority())
//                .build();
//
//        if (result.isPresent()) {
//            return response;
//        } else {
//            return null;
//        }
//
//    }

}