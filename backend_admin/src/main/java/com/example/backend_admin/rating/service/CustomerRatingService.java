package com.example.backend_admin.rating.service;

import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.customer.model.entity.Customer;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
import com.example.backend_admin.orders.model.entity.Orders;
import com.example.backend_admin.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerRatingService {
    private final CustomerRepository customerRepository;
    private final OrdersRepository ordersRepository;
    private final LoginLogRespository loginLogRespository;
    public Object rating() {
        List<Customer> customerList = customerRepository.findAll();
        List<Orders> ordersList = new ArrayList<>();
        List<LoginLog> loginLogs = new ArrayList<>();
        Integer totalAmount = 0;
        Integer count = 0;
        for(Customer customer : customerList){
            ordersList = ordersRepository.findByCustomerIdx(customer.getIdx());
            for(Orders orders : ordersList){
                totalAmount += orders.getProductPrice();
            }

            loginLogs  = loginLogRespository.findByCustomerIdx(customer.getIdx());
            for(LoginLog log : loginLogs) {
                count++;
            }

            System.out.println(totalAmount + " " + count);
        }
        return BaseResponse.successResponse("모든 고객에 대해 등급 산정이 완료되었습니다.", null);
    }

}
