package com.example.backend_admin.dashboard.service;

import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.dashboard.model.response.GetCustomerListRes;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/admin")
public class DashboardService {
    private final CustomerRepository customerRepository;
    private final LoginLogRespository loginLogRespository;

    public BaseResponse<List<GetCustomerListRes>> selectByLevel(CustomerLevel level){
        List<Customer> customerList = customerRepository.findByLevel(level);
        List<GetCustomerListRes> result = new ArrayList<>();
        for(Customer customer:customerList){
            result.add(GetCustomerListRes.builder().idx(customer.getIdx())
                            .customerEmail(customer.getCustomerEmail())
                            .level(customer.getLevel())
                            .totalAmount(customer.getTotalAmount())
                            .build());
        }

        return BaseResponse.successResponse(level+"등급 회원 목록입니다.", result);
    }

    public BaseResponse<List<GetCustomerListRes>> selectByAmount() {
        List<Customer> customerList = customerRepository.findAllByOrderByTotalAmountDesc();
        List<GetCustomerListRes> result = new ArrayList<>();
        for(Customer customer:customerList){
            result.add(GetCustomerListRes.builder()
                            .idx(customer.getIdx())
                            .customerEmail(customer.getCustomerEmail())
                            .level(customer.getLevel())
                            .totalAmount(customer.getTotalAmount())
                            .build());
        }
        return BaseResponse.successResponse("총 주문 금액 순 회원 목록입니다.", result);
    }

//    public void countActiveUser(){
//        loginLogRespository
//    }
}
