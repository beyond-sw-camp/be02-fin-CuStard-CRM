package com.example.backend_admin.calculate.service;

import com.example.backend_admin.calculate.model.response.GetTodayLoginRes;
import com.example.backend_admin.calculate.model.response.GetTodaySignupRes;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CalculateLogService {
    private final LoginLogRespository loginLogRespository;
    private final CustomerRepository customerRepository;
    private LocalDateTime today = LocalDateTime.now();
    public GetTodayLoginRes todayLogin(){
        Long todayLogin = loginLogRespository.countByCreatedDateAfter(today.minusDays(1));
        Long fromYesterdayLogin = loginLogRespository.countByCreatedDateAfter(today.minusDays(2));

        return GetTodayLoginRes.builder()
                .todayLogin(todayLogin)
                .difLogin(2*todayLogin - fromYesterdayLogin)
                .build();
    }

    public GetTodaySignupRes todaySignUp(){
        Long todaySignup = customerRepository.countByCreatedDateAfter(today.minusDays(1));
        Long fromYesterdaySignup = customerRepository.countByCreatedDateAfter(today.minusDays(2));
        return GetTodaySignupRes.builder()
                .todaySignup(todaySignup)
                .difSignup(2*todaySignup - fromYesterdaySignup)
                .build();

    }

}
