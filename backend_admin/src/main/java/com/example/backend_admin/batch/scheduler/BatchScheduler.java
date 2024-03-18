package com.example.backend_admin.batch.scheduler;


import com.example.backend_admin.batch.model.request.LevelCouponWriterReq;

import com.example.backend_admin.batch.model.request.SleeperCouponWriterReq;

import com.example.backend_admin.batch.model.response.LevelCouponReaderRes;

import com.example.backend_admin.batch.model.response.SleeperCouponReaderRes;
import com.example.backend_admin.batch.service.BatchService;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.log.entity.ProductDetailLog;
import com.example.backend_admin.log.repository.LoginLogRespository;
import com.example.backend_admin.product.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Component
@RequiredArgsConstructor

public class BatchScheduler {
    private final BatchService batchService;
    private final LoginLogRespository loginLogRespository;

    @Scheduled(cron = "40 * * * * *")
    public void batch() throws MessagingException {
        // 읽는거

        SleeperCouponReaderRes sleeperCouponReaderRes = batchService.sleeperCouponReader();
        LevelCouponReaderRes levelCouponReaderRes = batchService.levelCouponReader();
        System.out.println("Reader실행");


        // 처리

        System.out.println("Processor실행");


        // 결과 저장
        batchService.sleeperCouponWriter(SleeperCouponWriterReq.builder()
                .targetList(sleeperCouponReaderRes.getTargetList())
                .build());

        batchService.levelCouponWriter(LevelCouponWriterReq.builder()
                .targetList(levelCouponReaderRes.getTargetList())
                .build());
        System.out.println("Writer실행");

    }
}
