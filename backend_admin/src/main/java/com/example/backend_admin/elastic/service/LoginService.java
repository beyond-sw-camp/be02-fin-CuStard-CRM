package com.example.backend_admin.elastic.service;

import com.example.backend_admin.elastic.model.entity.LoginDocument;
import com.example.backend_admin.elastic.model.dto.GetLoginTimeRes;
import com.example.backend_admin.elastic.model.dto.GetTodayLoginRes;
import com.example.backend_admin.elastic.repository.LoginDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginDocumentRepository loginDocumentRepository;

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
}