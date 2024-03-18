package com.example.backend_admin.batch.service;

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
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.email.service.EmailService;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BatchService {
    private final CustomerRepository customerRepository;
    private final LoginLogRespository loginLogRespository;
    private final AdminService adminService;
    private final EmailService emailService;
    public void batch(){

        System.out.println("출력");
    }

    public SleeperCouponReaderRes sleeperCouponReader(){


        List<Long> targetList = loginLogRespository.findBySleeperCouponTargetList();


        return SleeperCouponReaderRes.builder()
                .targetList(targetList)
                .build();
    }


    public void sleeperCouponProcessor(){

    }

    public void sleeperCouponWriter(SleeperCouponWriterReq sleeperCouponWriterReq) throws MessagingException {
        adminService.sleeperCoupon(PostAdminSleeperCouponReq.builder()
                .targetList(sleeperCouponWriterReq.getTargetList())
                .build());
        emailService.sendSleeperEmail(sleeperCouponWriterReq.getTargetList());

    }
    public LevelCouponReaderRes levelCouponReader(){

        List<List<Long>> targetList = new ArrayList<>();
        List<Long> newbieList = new ArrayList<>();
        List<Long> bronzeList = new ArrayList<>();
        List<Long> silverList = new ArrayList<>();
        List<Long> goldList = new ArrayList<>();
        List<Long> platinumList = new ArrayList<>();
        List<Long> diamondList = new ArrayList<>();
        List<Customer> customerNewbieList = customerRepository.findByLevel(CustomerLevel.NEWBIE);
        for (Customer customer:customerNewbieList) {
            newbieList.add(customer.getIdx());
        }
        List<Customer> customerBronzeList = customerRepository.findByLevel(CustomerLevel.BRONZE);
        for (Customer customer:customerBronzeList) {
            bronzeList.add(customer.getIdx());
        }
        List<Customer> customerSilverList = customerRepository.findByLevel(CustomerLevel.SILVER);
        for (Customer customer:customerSilverList) {
            silverList.add(customer.getIdx());
        }
        List<Customer> customerGoldList = customerRepository.findByLevel(CustomerLevel.GOLD);
        for (Customer customer:customerGoldList) {
            goldList.add(customer.getIdx());
        }
        List<Customer> customerPlatinumList = customerRepository.findByLevel(CustomerLevel.PLATINUM);
        for (Customer customer:customerPlatinumList) {
            platinumList.add(customer.getIdx());
        }
        List<Customer> customerDiamondList = customerRepository.findByLevel(CustomerLevel.DIAMOND);
        for (Customer customer:customerDiamondList) {
            diamondList.add(customer.getIdx());
        }

        targetList.add(newbieList);
        targetList.add(bronzeList);
        targetList.add(silverList);
        targetList.add(goldList);
        targetList.add(platinumList);
        targetList.add(diamondList);

        return LevelCouponReaderRes.builder()
                .targetList(targetList)
                .build();
    }
    public void levelCouponProcessor(){

    }
    public ResponseEntity levelCouponWriter(LevelCouponWriterReq levelCouponWriterReq) throws MessagingException{
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
