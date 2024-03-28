package com.example.backend_admin.dashboard.service;

import com.example.backend_admin.dashboard.model.dto.GetLoginTimeRes;
import com.example.backend_admin.dashboard.model.dto.GetSleepAccountRes;
import com.example.backend_admin.dashboard.model.dto.GetTodayLoginRes;
import com.example.backend_admin.dashboard.model.dto.GetTodaySignupRes;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateLogService {
    private final LoginLogRespository loginLogRespository;
    private final CustomerRepository customerRepository;
    private LocalDateTime today = LocalDateTime.now();
    public GetTodayLoginRes todayLogin() throws BaseException {
        Long todayLogin = loginLogRespository.countByCreatedDateAfter(today.minusDays(1));
        Long fromYesterdayLogin = loginLogRespository.countByCreatedDateAfter(today.minusDays(2));

        return GetTodayLoginRes.builder()
                .todayLogin(todayLogin)
                .difLogin(2*todayLogin - fromYesterdayLogin)
                .build();
    }

    public GetTodaySignupRes todaySignUp()throws BaseException{
        Long todaySignup = customerRepository.countByCreatedDateAfter(today.minusDays(1));
        Long fromYesterdaySignup = customerRepository.countByCreatedDateAfter(today.minusDays(2));
        return GetTodaySignupRes.builder()
                .todaySignup(todaySignup)
                .difSignup(2*todaySignup - fromYesterdaySignup)
                .build();

    }

    public GetSleepAccountRes sleepAccountGrowthRate() throws BaseException{
        double sleepAccountGrowthRate = ((loginLogRespository.findByAcountCountToday()-loginLogRespository.findByTodaysAgoActiveCount())-(loginLogRespository.findByAcountCountDayAgo()-loginLogRespository.findByOneDaysAgoActiveCount()));
        System.out.println("현재 : " + (loginLogRespository.findByAcountCountToday()-loginLogRespository.findByTodaysAgoActiveCount()));
        System.out.println("이전 : " + (loginLogRespository.findByAcountCountDayAgo()-loginLogRespository.findByOneDaysAgoActiveCount()));

        Long todaySleepAccount = loginLogRespository.findByAcountCountToday()-loginLogRespository.findByTodaysAgoActiveCount();
        Long difSleepAccount = loginLogRespository.findByAcountCountDayAgo()-loginLogRespository.findByOneDaysAgoActiveCount();
        return GetSleepAccountRes.builder()
                .todaySleepAccount(todaySleepAccount)
                .difSleepAccount(difSleepAccount)
                .build();
    }

    public GetLoginTimeRes loginTime()throws BaseException{
        int[] array = new int[24];

        List<LoginLog> loginLogs = loginLogRespository.findByCreatedDateAfter(today.minusDays(14));

        for(LoginLog log:loginLogs){
            Integer hour = log.getCreatedDate().getHour();
            array[hour]++;
        }

        return GetLoginTimeRes.builder().timeDataList(array).build();
    }

    public GetLoginTimeRes customerLoginTime(Long idx)throws BaseException{
        int[] array = new int[24];

        List<LoginLog> loginLogs = loginLogRespository.findByCustomerIdxAndCreatedDateAfter(idx,today.minusDays(365));

        for(LoginLog log:loginLogs){
            Integer hour = log.getCreatedDate().getHour();
            array[hour]++;
        }

        return GetLoginTimeRes.builder().timeDataList(array).build();
    }
}
