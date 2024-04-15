package com.example.backend_admin.elastic.service;

import com.example.backend_admin.elastic.model.entity.CustomerDocument;
import com.example.backend_admin.elastic.repository.CustomerDocumentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ElasticCustomerService {
    private final CustomerDocumentRepository customerDocumentRepository;

    public Page<CustomerDocument> dashboard(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("idx").ascending());
        return customerDocumentRepository.findAll(pageable);
    }

    public Page<CustomerDocument> amtDescDashboard(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("totalAmount").descending());
        return customerDocumentRepository.findAll(pageable);
    }

    public Page<CustomerDocument> amtAscDashboard(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("totalAmount").ascending());
        return customerDocumentRepository.findAll(pageable);
    }

    public Page<CustomerDocument> getCustomersByLevel(Integer level, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("idx").ascending());
        return customerDocumentRepository.findByLevel(level, pageable);
    }
}
