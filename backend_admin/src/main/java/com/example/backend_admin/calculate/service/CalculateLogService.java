package com.example.backend_admin.calculate.service;

import com.example.backend_admin.calculate.model.response.GetSleepAccountGrowthRateRes;
import com.example.backend_admin.calculate.model.response.GetTodayLoginByThreeHour;
import com.example.backend_admin.calculate.model.response.GetTodayLoginRes;
import com.example.backend_admin.calculate.model.response.GetTodaySignupRes;
import com.example.backend_admin.customer.repository.CustomerRepository;
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
        double sleepAccountGrowthRate = ((customerRepository.count() - loginLogRespository.findByOneDaysAgoActiveCount()) - (customerRepository.count() - loginLogRespository.findByTwoDaysAgoActiveCount()))/(double)loginLogRespository.findByTwoDaysAgoActiveCount()*100;
        System.out.println("최종 : " + loginLogRespository.findByOneDaysAgoActiveCount());
        System.out.println("초기 : " + loginLogRespository.findByTwoDaysAgoActiveCount());
        sleepAccountGrowthRate = Math.round(sleepAccountGrowthRate*100)/100.0;

        return GetSleepAccountGrowthRateRes.builder()
                .sleepAccountGrowthRate(sleepAccountGrowthRate).build();
    }

    public GetTodayLoginByThreeHour todayLoginByThreeHour(){
        List<List<Long>> countList = loginLogRespository.findByLoginByThreeHour();
        Long sectionCount = 0L;
        int time=0;
        List<Long> timeDataList = new ArrayList<>();
        for (List<Long> count:countList) {
            System.out.println(count.get(1));
            sectionCount = sectionCount + count.get(1);
            time++;
            if (time%3==0){
                timeDataList.add(sectionCount);
                sectionCount=0L;
            }
        }

        return GetTodayLoginByThreeHour.builder().timeDataList(timeDataList).build();
    }
}
