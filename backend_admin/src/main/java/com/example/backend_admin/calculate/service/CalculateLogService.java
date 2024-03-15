package com.example.backend_admin.calculate.service;

import com.example.backend_admin.calculate.model.response.GetTodayLoginRes;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CalculateLogService {
    private final LoginLogRespository loginLogRespository;
    public GetTodayLoginRes todayLogin(){
        Long todayLogin = loginLogRespository.countByCreatedDateAfter(LocalDateTime.now().minusDays(1));
        Long yesterdayLogin = loginLogRespository.countByCreatedDateAfter(LocalDateTime.now().minusDays(2));
        System.out.println(todayLogin + " " + yesterdayLogin);
        return GetTodayLoginRes.builder()
                .todayLogin(todayLogin)
                .difLogin(2*todayLogin - yesterdayLogin)
                .build();
    }
}
