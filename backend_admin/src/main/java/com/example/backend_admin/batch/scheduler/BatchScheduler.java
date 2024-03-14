package com.example.backend_admin.batch.scheduler;

import com.example.backend_admin.batch.model.request.LevelCouponProcessorReq;
import com.example.backend_admin.batch.model.request.LevelCouponWriterReq;
import com.example.backend_admin.batch.model.request.SleeperCouponProcessorReq;
import com.example.backend_admin.batch.model.request.SleeperCouponWriterReq;
import com.example.backend_admin.batch.model.response.LevelCouponProcessorRes;
import com.example.backend_admin.batch.model.response.LevelCouponReaderRes;
import com.example.backend_admin.batch.model.response.SleeperCouponProcessorRes;
import com.example.backend_admin.batch.model.response.SleeperCouponReaderRes;
import com.example.backend_admin.batch.service.BatchService;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.log.entity.ProductDetailLog;
import com.example.backend_admin.product.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor

public class BatchScheduler {
    private final BatchService batchService;


    @Scheduled(cron = "30 * * * * *")
    public void batch(){
        // 읽는거
//        batchService.batch();

        SleeperCouponReaderRes sleeperCouponReaderRes = batchService.sleeperCouponReader();
        System.out.println("Reader실행");
//        LevelCouponReaderRes levelCouponReaderRes = batchService.levelCouponReader();

        // 처리
        SleeperCouponProcessorRes sleeperCouponProcessorRes = batchService.sleeperCouponProcessor(SleeperCouponProcessorReq.builder()
                .loginLogLists(sleeperCouponReaderRes.getLoginLogLists())
                .build());
        System.out.println("Processor실행");
//        LevelCouponProcessorRes levelCouponProcessorRes = batchService.levelCouponProcessor(LevelCouponProcessorReq.builder()
//                .customerList(levelCouponReaderRes.getCustomerList())
//                .build());

        // 결과 저장
        batchService.sleeperCouponWriter(SleeperCouponWriterReq.builder()
                .couponRecipientList(sleeperCouponProcessorRes.getCouponRecipientList())
                .build());

        System.out.println("Writer실행");
//        batchService.levelCouponWriter(LevelCouponWriterReq.builder()
//                .targetList(levelCouponProcessorRes.getTargetList())
//                .build());
    }
}
