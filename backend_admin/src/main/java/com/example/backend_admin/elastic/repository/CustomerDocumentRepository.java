package com.example.backend_admin.elastic.repository;

import com.example.backend_admin.elastic.entity.CustomerDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerDocumentRepository extends ElasticsearchRepository<CustomerDocument, String> {

    List<CustomerDocument> findAll();
}
