package com.example.demo.job;

import com.example.demo.coupon.service.SetCouponTargetService;
import com.example.demo.common.CustomerLevel;
import com.example.demo.customer.model.entity.CustomerDocument;
import com.example.demo.customer.repository.CustomerDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.AbstractPagingItemReader;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CustomerRepositoryItemReader extends AbstractPagingItemReader<List<CustomerDocument>> {
    private final CustomerDocumentRepository customerDocumentRepository;
    private final SetCouponTargetService setCouponTargetService;

    @Override
    protected void doReadPage() {
        if (results == null) {
            results = new ArrayList<>();
        } else {
            results.clear();
        }
        List<String> levels = setCouponTargetService.extractLevels();
        for (String level : levels) {
            List<CustomerDocument> customers = customerDocumentRepository.findByLevel(CustomerLevel.valueOf(level).ordinal());
            results.add(customers);
        }
    }

    @Override
    protected void doJumpToPage(int i) {

    }
}
