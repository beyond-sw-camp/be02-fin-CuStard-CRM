package com.example.backend_admin.calculate.service;

import com.example.backend_admin.calculate.model.response.GetLoginTimeRes;
import com.example.backend_admin.calculate.model.response.GetSleepAccountGrowthRateRes;
import com.example.backend_admin.calculate.model.response.GetTodayLoginRes;
import com.example.backend_admin.calculate.model.response.GetTodaySignupRes;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public GetSleepAccountGrowthRateRes sleepAccountGrowthRate(){
        double sleepAccountGrowthRate = ((loginLogRespository.findByAcountCountToday()-loginLogRespository.findByTodaysAgoActiveCount())-(loginLogRespository.findByAcountCountDayAgo()-loginLogRespository.findByOneDaysAgoActiveCount()));
        System.out.println("현재 : " + (loginLogRespository.findByAcountCountToday()-loginLogRespository.findByTodaysAgoActiveCount()));
        System.out.println("이전 : " + (loginLogRespository.findByAcountCountDayAgo()-loginLogRespository.findByOneDaysAgoActiveCount()));
//        System.out.println(loginLogRespository.findByAcountCountToday());
//        System.out.println(loginLogRespository.findByTodaysAgoActiveCount());
//        System.out.println(loginLogRespository.findByAcountCountDayAgo());
//        System.out.println(loginLogRespository.findByOneDaysAgoActiveCount());
        System.out.println(sleepAccountGrowthRate);
//        sleepAccountGrowthRate = Math.round(sleepAccountGrowthRate*100)/100.0;

        return GetSleepAccountGrowthRateRes.builder()
                .sleepAccountGrowthRate(sleepAccountGrowthRate).build();
    }

    public GetLoginTimeRes loginTime(){
        int[] array = new int[24];

        List<LoginLog> loginLogs = loginLogRespository.findByCreatedDateAfter(today.minusDays(14));

        for(LoginLog log:loginLogs){
            Integer hour = log.getCreatedDate().getHour();
            array[hour]++;
        }

        return GetLoginTimeRes.builder().timeDataList(array).build();
    }

    public GetLoginTimeRes customerLoginTime(Long idx){
        int[] array = new int[24];

        List<LoginLog> loginLogs = loginLogRespository.findByCustomerIdxAndCreatedDateAfter(idx,today.minusDays(365));

        for(LoginLog log:loginLogs){
            Integer hour = log.getCreatedDate().getHour();
            array[hour]++;
        }

        return GetLoginTimeRes.builder().timeDataList(array).build();
    }
}
