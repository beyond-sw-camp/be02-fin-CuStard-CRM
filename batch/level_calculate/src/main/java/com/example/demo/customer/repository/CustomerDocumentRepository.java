package com.example.demo.customer.repository;

import com.example.demo.customer.model.entity.CustomerDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface CustomerDocumentRepository extends ElasticsearchRepository<CustomerDocument, String> {

}
