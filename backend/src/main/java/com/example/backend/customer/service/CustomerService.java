package com.example.backend.customer.service;




import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.model.request.PostCustomerLoginReq;
import com.example.backend.customer.model.request.PostCustomerSignupReq;
import com.example.backend.customer.model.response.*;
import com.example.backend.customer.repository.CustomerRepository;

import com.example.backend.havecoupon.model.entity.HaveCoupon;
import com.example.backend.havecoupon.model.response.GetHaveCouponBaseRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponListRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponReadRes;

import com.example.backend.log.service.LoginLogService;

import com.example.backend.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;


@Service
@RequiredArgsConstructor
@CrossOrigin("*")
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

    public GetCustomerReadRes read(Long idx){
        Optional<Customer> result = customerRepository.findById(idx);

        if (result.isPresent()){
            Customer customer = result.get();
            List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();

            for (HaveCoupon haveCoupon: customer.getHaveCouponList()) {
                getHaveCouponBaseResList.add(GetHaveCouponBaseRes.builder()
                        .idx(haveCoupon.getIdx())
                        .count(haveCoupon.getCount())
                        .build());
            }
            return GetCustomerReadRes.builder()
                    .idx(customer.getIdx())
                    .customerEmail(customer.getCustomerEmail())
                    .authority(customer.getAuthority())
                    .getHaveCouponBaseResList(getHaveCouponBaseResList)
                    .build();
        }
        return null;
    }
    public List<GetCustomerListRes> list(){
        List<Customer> result = customerRepository.findAll();

        List<GetCustomerListRes> getCustomerListResList = new ArrayList<>();

        for (Customer customer: result) {
            List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();


            for (HaveCoupon haveCoupon: customer.getHaveCouponList()) {
                GetHaveCouponBaseRes getHaveCouponBaseRes = GetHaveCouponBaseRes.builder()
                        .idx(haveCoupon.getIdx())
                        .count(haveCoupon.getCount())
                        .build();

                getHaveCouponBaseResList.add(getHaveCouponBaseRes);
            }

            GetCustomerListRes getCustomerListRes = GetCustomerListRes.builder()
                    .idx(customer.getIdx())
                    .customerEmail(customer.getCustomerEmail())
                    .authority(customer.getAuthority())
                    .getHaveCouponBaseResList(getHaveCouponBaseResList)
                    .build();

            getCustomerListResList.add(getCustomerListRes);

        }
        return getCustomerListResList;
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



}