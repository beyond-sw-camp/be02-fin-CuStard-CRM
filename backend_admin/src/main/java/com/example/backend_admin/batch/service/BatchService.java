package com.example.backend_admin.batch.service;

import com.example.backend_admin.coupon.service.SetCouponTargetService;
import com.example.backend_admin.admin.model.request.PostAdminLevelCouponReq;
import com.example.backend_admin.admin.model.request.PostAdminSleeperCouponReq;
import com.example.backend_admin.admin.service.AdminService;
import com.example.backend_admin.batch.model.request.LevelCouponWriterReq;
import com.example.backend_admin.batch.model.request.SleeperCouponWriterReq;
import com.example.backend_admin.batch.model.response.SleeperCouponReaderRes;
import com.example.backend_admin.batch.model.response.LevelCouponReaderRes;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.email.service.EmailService;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BatchService {
    private final CustomerRepository customerRepository;
    private final LoginLogRespository loginLogRespository;
    private final AdminService adminService;
    private final EmailService emailService;
    private final SetCouponTargetService setCouponTargetService;


    public SleeperCouponReaderRes sleeperCouponReader() {


        List<Long> targetList = loginLogRespository.findBySleeperCouponTargetList();

        return SleeperCouponReaderRes.builder()
                .targetList(targetList)
                .build();
    }


    public void sleeperCouponProcessor() {

    }

    public ResponseEntity sleeperCouponWriter(SleeperCouponWriterReq sleeperCouponWriterReq) throws MessagingException {
        try {
            adminService.sleeperCoupon(PostAdminSleeperCouponReq.builder()
                    .targetList(sleeperCouponWriterReq.getTargetList())
                    .build());
            emailService.sendSleeperEmail(sleeperCouponWriterReq.getTargetList());
            return ResponseEntity.ok().body("쿠폰 발급 완료");
        } catch (BaseException exception) {
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    public LevelCouponReaderRes levelCouponReader() {

        List<List<Long>> targetList = new ArrayList<>();
        List<String> levels = setCouponTargetService.extractLevels();

        if (levels != null) {
            for (String level : levels) {
                CustomerLevel enumLevel = CustomerLevel.valueOf(level);
                Integer levelNum = enumLevel.ordinal();
                List<Long> customerIds = customerRepository.findCustomerIdxByLevel(levelNum);
                targetList.add(customerIds);
            }
        }
        return LevelCouponReaderRes.builder()
                .targetList(targetList)
                .build();
    }
    public void levelCouponProcessor() {

    }
    public ResponseEntity levelCouponWriter(LevelCouponWriterReq levelCouponWriterReq) throws MessagingException {
        try {
            adminService.levelCoupon(PostAdminLevelCouponReq.builder()
                    .targetList(levelCouponWriterReq.getTargetList())
                    .build());
            emailService.sendLevelCoupon(levelCouponWriterReq.getTargetList());
            return ResponseEntity.ok().body("쿠폰 생성 완료");
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }


    }

}
