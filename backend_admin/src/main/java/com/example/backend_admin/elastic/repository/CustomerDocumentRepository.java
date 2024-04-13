package com.example.backend_admin.elastic.repository;

import com.example.backend_admin.elastic.model.entity.CustomerDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerDocumentRepository extends ElasticsearchRepository<CustomerDocument, String> {

    Page<CustomerDocument> findByLevel(Integer level, Pageable pageable);
    List<CustomerDocument> findAll();
}
