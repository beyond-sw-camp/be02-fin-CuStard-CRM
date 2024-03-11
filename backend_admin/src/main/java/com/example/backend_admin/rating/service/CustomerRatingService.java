package com.example.backend_admin.rating.service;

import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.customer.entity.Customer;
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

    public BaseResponse<String> rating() {
        List<Customer> customerList = customerRepository.findAll();
        List<Orders> ordersList;
        List<LoginLog> loginLogs;
        Integer totalAmount=0, count = 0;
        for(Customer customer : customerList){
            ordersList = ordersRepository.findByCustomerIdx(customer.getIdx());
            for(Orders orders : ordersList){
                totalAmount += orders.getProductPrice();
            }

            loginLogs  = loginLogRespository.findByCustomerIdx(customer.getIdx());
            for(LoginLog log : loginLogs) {
                count++;
            }

            CustomerLevel level = calculateLevel(totalAmount * 8 + count * 2);

            customer.setLevel(level);
            customerRepository.save(customer);
        }
        return BaseResponse.successResponse("모든 고객에 대해 등급 산정이 완료되었습니다.", null);
    }

    public CustomerLevel calculateLevel(Integer score){
        CustomerLevel level;
        if (score >= 100000){
            level = CustomerLevel.DIAMOND;
        } else if (score >= 80000) {
            level = CustomerLevel.PLATINUM;
        } else if (score >= 60000) {
            level = CustomerLevel.GOLD;
        }else if (score >= 4000) {
            level = CustomerLevel.SILVER;
        }else if (score >= 2000) {
            level = CustomerLevel.BRONZE;
        }else {
            level = CustomerLevel.NEWBIE;
        }
        return level;
    }

}
