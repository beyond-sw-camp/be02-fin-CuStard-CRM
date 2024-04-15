package com.example.demo.customer.repository;

import com.example.demo.common.CustomerLevel;
import com.example.demo.customer.model.entity.CustomerDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerDocumentRepository extends ElasticsearchRepository<CustomerDocument, String> {

    List<CustomerDocument> findByLevel(Integer level);
}
