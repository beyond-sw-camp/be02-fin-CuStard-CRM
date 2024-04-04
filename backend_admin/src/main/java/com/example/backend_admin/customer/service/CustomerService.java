package com.example.backend_admin.customer.service;


import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.customer.entity.response.GetCustomerListRes;
import com.example.backend_admin.customer.entity.response.GetCustomerReadRes;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.havecoupon.model.entity.HaveCoupon;
import com.example.backend_admin.havecoupon.model.entity.response.GetHaveCouponBaseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.*;
import static com.example.backend_admin.common.BaseResponseStatus.CUSTOMER_READ_EMPTY;
import static com.example.backend_admin.common.BaseResponseStatus.CUSTOMER_LIST_EMPTY;



@Service
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public Customer getMemberByEmail (String customerEmail){
        return customerRepository.findByCustomerEmail(customerEmail).get();
    }

    public GetCustomerReadRes read(Long idx) throws BaseException{
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
                    .name(customer.getUsername())
                    .customerEmail(customer.getCustomerEmail())
                    .authority(customer.getAuthority())
                    .getHaveCouponBaseResList(getHaveCouponBaseResList)
                    .totalAmount(customer.getTotalAmount())
                    .level(customer.getLevel())
                    .age(customer.getAge())
                    .address(customer.getAddress())
                    .gender(customer.getGender())
                    .lastLogin(customer.getLastLogin())
                    .build();
        }
        throw new BaseException(CUSTOMER_READ_EMPTY);
    }
    public List<GetCustomerListRes> list()throws BaseException{
        List<Customer> result = customerRepository.findAll();

        List<GetCustomerListRes> getCustomerListResList = new ArrayList<>();
        if (result.isEmpty()){
            throw new BaseException(CUSTOMER_LIST_EMPTY);
        }
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
                    .name(customer.getUsername())
                    .customerEmail(customer.getCustomerEmail())
                    .authority(customer.getAuthority())
                    .getHaveCouponBaseResList(getHaveCouponBaseResList)
                    .totalAmount(customer.getTotalAmount())
                    .level(customer.getLevel())
                    .lastLogin(customer.getLastLogin())
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
        if(result.isEmpty()) {
            throw new UsernameNotFoundException("회원이 존재하지 않습니다.");
        }
        customer = result.get();
        return customer;
    }


}
