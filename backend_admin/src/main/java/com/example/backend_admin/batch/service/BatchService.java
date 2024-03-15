package com.example.backend_admin.batch.service;

import com.example.backend_admin.admin.model.request.PostAdminLevelCouponReq;
import com.example.backend_admin.admin.model.request.PostAdminSleeperCouponReq;
import com.example.backend_admin.admin.service.AdminService;
import com.example.backend_admin.batch.model.request.LevelCouponWriterReq;
import com.example.backend_admin.batch.model.request.SleeperCouponWriterReq;
import com.example.backend_admin.batch.model.response.SleeperCouponReaderRes;
import com.example.backend_admin.batch.model.response.LevelCouponReaderRes;
import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.customer.repository.CustomerRepository;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void sleeperCouponWriter(SleeperCouponWriterReq sleeperCouponWriterReq){
        adminService.sleeperCoupon(PostAdminSleeperCouponReq.builder()
                .targetList(sleeperCouponWriterReq.getTargetList())
                .build());


    }
    public LevelCouponReaderRes levelCouponReader(){

        List<List<Long>> targetList = new ArrayList<>();

        List<Long> newbieList = loginLogRespository.findByNewbieList();
        List<Long> bronzeList = loginLogRespository.findByBronzeList();
        List<Long> silverList = loginLogRespository.findBySilverList();
        List<Long> goldList = loginLogRespository.findByGoldList();
        List<Long> platinumList = loginLogRespository.findByPlatinumList();
        List<Long> diamondList = loginLogRespository.findByDiamondList();

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
    public void levelCouponWriter(LevelCouponWriterReq levelCouponWriterReq){
        adminService.levelCoupon(PostAdminLevelCouponReq.builder()
                .targetList(levelCouponWriterReq.getTargetList())
                .build());
    }

}
