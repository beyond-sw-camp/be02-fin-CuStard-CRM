package com.example.backend_admin.batch.scheduler;

import com.example.backend_admin.batch.service.BatchService;
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


    @Scheduled(cron = "* * * * * *")
    public void batch(){
        // 읽는거
        batchService.batch();



        // 처리
//        batchService.recommandProccessor(productList, productDetailLogList);
        // 결과 저장
    }
}
