package com.example.backend.log.service;

import com.example.backend.customer.model.entity.Customer;
import com.example.backend.log.entity.LoginLog;
import com.example.backend.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginLogService {
    private final LoginLogRespository loginLogRespository;

    public void loginLogging(Customer customer){
        loginLogRespository.save(LoginLog.builder()
                        .customer(customer)
                        .build());
    }
}
