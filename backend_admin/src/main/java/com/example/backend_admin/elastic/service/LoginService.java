package com.example.backend_admin.elastic.service;

import com.example.backend_admin.elastic.entity.CustomerDocument;
import com.example.backend_admin.elastic.entity.LoginDocument;
import com.example.backend_admin.elastic.entity.OrdersDocument;
import com.example.backend_admin.elastic.model.dto.GetLoginTimeRes;
import com.example.backend_admin.elastic.model.dto.GetTodayLoginRes;
import com.example.backend_admin.elastic.repository.CustomerDocumentRepository;
import com.example.backend_admin.elastic.repository.LoginDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginDocumentRepository loginDocumentRepository;
    private final CustomerDocumentRepository customerDocumentRepository;
    //오늘 방문자 수, 어제 동시간대 방문자 수
    public GetTodayLoginRes countTodayLogins() {
        ZoneId zoneId = ZoneId.of("Asia/Seoul");

        // 오늘 00시
        LocalDateTime startOfToday = LocalDate.now(zoneId).atStartOfDay().plusHours(9);
        // 현재 시간
        LocalDateTime now = LocalDateTime.now(zoneId).plusHours(9);
        // 전날 00시
        LocalDateTime startOfYesterday = startOfToday.minusDays(1);
        // 전날 동시간대
        LocalDateTime sameTimeYesterday = now.minusDays(1);

        // LocalDateTime을 Date로 변환
        Date startOfTodayDate = Date.from(startOfToday.atZone(zoneId).toInstant());
        Date nowDate = Date.from(now.atZone(zoneId).toInstant());
        Date startOfYesterdayDate = Date.from(startOfYesterday.atZone(zoneId).toInstant());
        Date sameTimeYesterdayDate = Date.from(sameTimeYesterday.atZone(zoneId).toInstant());

        long todayLoginsCount =loginDocumentRepository.countByTimestampBetween(startOfTodayDate, nowDate);
        long yestLoginsCount = loginDocumentRepository.countByTimestampBetween(startOfYesterdayDate, sameTimeYesterdayDate);

        System.out.println("오늘 방문자: "+ todayLoginsCount + " , 어제 동시간대 방문자: " + yestLoginsCount);

        return GetTodayLoginRes.builder()
                .todayLogin(todayLoginsCount)
                .difLogin(todayLoginsCount-yestLoginsCount)
                .build();
    }

    //시간대별 로그인
    public GetLoginTimeRes countLoginTime() {
        LocalDateTime start = LocalDate.now().minusDays(14).atStartOfDay();
        Date startDate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());

        List<LoginDocument> list = loginDocumentRepository.findByTimestampAfter(startDate);

        int[] loginTime = new int[24];
        for(LoginDocument login : list) {
            loginTime[login.getTimestamp().getHours()]++;
        }

        System.out.println( "시간대별 로그인: [" + loginTime[0] + "," + loginTime[1] + "," + loginTime[2] + ","  +loginTime[3] + "," +
                loginTime[4] + loginTime[5] + "," + loginTime[6] + "," + loginTime[7] + ","  +loginTime[8] + "," +
                loginTime[9] + loginTime[10] + "," + loginTime[11] +
                loginTime[12] + loginTime[13] + "," + loginTime[14] + "," + loginTime[15] + ","  +loginTime[16] + "," +
                loginTime[17] + loginTime[18] + "," + loginTime[19] +loginTime[20] + loginTime[21] + "," + loginTime[22] +
                + loginTime[23] +"]");

        return GetLoginTimeRes.builder()
                .timeDataList(loginTime)
                .build();
    }


    public GetLoginTimeRes custLoginTime(Long idx) {
        LocalDateTime start = LocalDate.now().minusDays(365).atStartOfDay();
        Date startDate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());

        List<LoginDocument> list = loginDocumentRepository.findByCustomerIdxAndTimestampAfter(idx ,startDate);

        int[] loginTime = new int[24];
        for(LoginDocument login : list) {
            loginTime[login.getTimestamp().getHours()]++;
        }

        System.out.println( "고객의 시간대별 로그인: [" + loginTime[0] + "," + loginTime[1] + "," + loginTime[2] + ","  +loginTime[3] + "," +
                loginTime[4] + loginTime[5] + "," + loginTime[6] + "," + loginTime[7] + ","  +loginTime[8] + "," +
                loginTime[9] + loginTime[10] + "," + loginTime[11] +
                loginTime[12] + loginTime[13] + "," + loginTime[14] + "," + loginTime[15] + ","  +loginTime[16] + "," +
                loginTime[17] + loginTime[18] + "," + loginTime[19] +loginTime[20] + loginTime[21] + "," + loginTime[22] +
                + loginTime[23] +"]");
        return GetLoginTimeRes.builder()
                .timeDataList(loginTime)
                .build();
    }

    public List<LoginDocument> testall (){
        List<LoginDocument> loginDocumentList = loginDocumentRepository.findAll();

        return loginDocumentList;
    }

    public List<Long> between90(){
        LocalDate currentDate = LocalDate.now();    //  현재 날짜
        LocalDate past90DaysDate = currentDate.minusDays(90);   //  90일 전
        //  localdate to date
        Date currentDateAsDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date past90DaysDateAsDate = Date.from(past90DaysDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<LoginDocument> loginDocumentList = loginDocumentRepository.findByTimestampBetween(past90DaysDateAsDate, currentDateAsDate);
        // 중복 제거
        List<LoginDocument> uniqueLogins = loginDocumentList.stream()
                .collect(Collectors.toMap(LoginDocument::getCustomerIdx, login -> login, (existing, replacement) -> existing))
                .values().stream()
                .collect(Collectors.toList());


        List<Long> loginCustomerIdxList = new ArrayList<>();
        //  현재 ~ 90일 사이에 로그인 한 idx 리스트
        for (LoginDocument document: uniqueLogins) {
            long customerIdx = document.getCustomerIdx();   // 불필요한 박싱 제거
            loginCustomerIdxList.add(customerIdx);
        }

        List<CustomerDocument> customerDocumentList = new ArrayList<>();
        List<Long> sleepCustomerList = new ArrayList<>();
        customerDocumentList = customerDocumentRepository.findAll();    //  전체 회원 document

//        List<Long> asdflkjlist = new ArrayList<>();
//        List<LoginDocument> list = new ArrayList<>();
//        list = loginDocumentRepository.findAll();
//        for (LoginDocument document:list) {
//            asdflkjlist.add(Long.valueOf(document.getCustomerIdx()));
//        }


        for (CustomerDocument document: customerDocumentList) {         //  휴면 회원만 뽑기   //TODO: 지금은 전체 회원이 없음
            if (!loginCustomerIdxList.contains(document.getCustomerIdx())){
                sleepCustomerList.add(document.getCustomerIdx());
            }
        }

        return sleepCustomerList;
    }

    public void toRatingCoupon(){
        List<CustomerDocument> customerDocumentList = customerDocumentRepository.findAll();


    }



}