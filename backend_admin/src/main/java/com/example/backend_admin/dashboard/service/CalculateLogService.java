package com.example.backend_admin.dashboard.service;

import com.example.backend_admin.dashboard.model.dto.GetLoginTimeRes;
import com.example.backend_admin.dashboard.model.dto.GetSleepAccountRes;
import com.example.backend_admin.dashboard.model.dto.GetTodayLoginRes;
import com.example.backend_admin.dashboard.model.dto.GetTodaySignupRes;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.elastic.model.entity.CustomerDocument;
import com.example.backend_admin.elastic.model.entity.LoginDocument;
import com.example.backend_admin.elastic.repository.CustomerDocumentRepository;
import com.example.backend_admin.elastic.repository.LoginDocumentRepository;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculateLogService {
    private final LoginLogRespository loginLogRespository;
    private final CustomerRepository customerRepository;
    private final LoginDocumentRepository loginDocumentRepository;
    private final CustomerDocumentRepository customerDocumentRepository;
    private LocalDateTime today = LocalDateTime.now();
    private Long cachedCount = 0L;
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
        ZoneId zoneId = ZoneId.of("Asia/Seoul");

        // 오늘 00시
        LocalDateTime startOfToday = LocalDate.now(zoneId).atStartOfDay();

        // 현재 시간
        LocalDateTime now = LocalDateTime.now(zoneId);
        LocalDateTime past1DaysDate = now.minusDays(1).plusHours(9);
        LocalDateTime past91DaysDate = now.minusDays(91).plusHours(9);


        Date past1DaysDateAsDate = Date.from(past1DaysDate.atZone(zoneId).toInstant());
        Date past91DaysDateAsDate = Date.from(past91DaysDate.atZone(zoneId).toInstant());

        System.out.println();
        System.out.println();
        System.out.println(past1DaysDateAsDate);
        System.out.println(past91DaysDateAsDate);
        System.out.println();
        System.out.println();

//        Date startOfTodayDate = Date.from(startOfToday.atZone(zoneId).toInstant());
//        Date nowDate = Date.from(now.atZone(zoneId).toInstant());
//
//        LocalDate currentDate = LocalDate.now();    //  현재 날짜
//        LocalDate past1DaysDate = currentDate.minusDays(1);   //  1일 전
//        LocalDate past91DaysDate = currentDate.minusDays(91);   //  91일 전
//        LocalDate past92DaysDate = currentDate.minusDays(92);   //  92일 전
//        //  localdate to date
//        Date currentDateAsDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date past1DaysDateAsDate = Date.from(past1DaysDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date past91DaysDateAsDate = Date.from(past91DaysDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<LoginDocument> loginDocumentList = loginDocumentRepository.findByTimestampBetween(past91DaysDateAsDate, past1DaysDateAsDate);
        // 중복 제거
        List<LoginDocument> uniqueLogins = loginDocumentList.stream()
                .collect(Collectors.toMap(LoginDocument::getCustomerIdx, login -> login, (existing, replacement) -> existing))
                .values().stream()
                .collect(Collectors.toList());


        List<Long> loginCustomerIdxList = new ArrayList<>();
        //  1일전 ~ 91일전 사이에 로그인 한 idx 리스트
        for (LoginDocument document: uniqueLogins) {
            long customerIdx = document.getCustomerIdx();   // 불필요한 박싱 제거
            loginCustomerIdxList.add(customerIdx);
        }

        List<CustomerDocument> customerDocumentList = new ArrayList<>();
        List<String> sleepCustomerList = new ArrayList<>();
        customerDocumentList = customerDocumentRepository.findAll();    //  전체 회원 document

        for (CustomerDocument document: customerDocumentList) {         //  휴면 회원만 뽑기
            if (!loginCustomerIdxList.contains(document.getIdx())){

                sleepCustomerList.add(document.getIdx());
            }
        }

        List<LoginDocument> todayLoginList = loginDocumentRepository.findByTimestampAfter(past1DaysDateAsDate); //  하루전 정각 이후 로그인 리스트
        //  중복 제거
        List<LoginDocument> uniqueTodayLogins = todayLoginList.stream()
                .collect(Collectors.toMap(LoginDocument::getCustomerIdx, login -> login, (existing, replacement) -> existing))
                .values().stream()
                .collect(Collectors.toList());
        Long count = 0L;
        for (LoginDocument document:uniqueTodayLogins) {
            if(sleepCustomerList.contains(document.getCustomerIdx())){
                count++;
            }
        }


        Long countTwoDayAgo = cachedCount;
        cachedCount = count;





        return GetSleepAccountRes.builder()
                .difSleepAccount(countTwoDayAgo)
                .todaySleepAccount(count)
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
