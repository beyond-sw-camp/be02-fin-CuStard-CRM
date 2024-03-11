package com.example.backend.log.service;

import com.example.backend.customer.model.entity.Customer;
import com.example.backend.log.entity.ProductDetailLog;
import com.example.backend.log.entity.SearchLog;
import com.example.backend.log.repository.SearchLogRepository;
import com.example.backend.product.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class SearchLogService {
    private final SearchLogRepository searchLogRepository;
    @RequestMapping("/search/logging")
    public void SearchLogging(Customer customer, String keyword){
        searchLogRepository.save(SearchLog.builder()
                .customer(customer)
                .keyword(keyword)
                .build());
    }
}
