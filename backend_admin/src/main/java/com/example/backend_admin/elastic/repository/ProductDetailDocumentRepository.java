package com.example.backend_admin.elastic.repository;

import com.example.backend_admin.elastic.model.entity.ProductDetailDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductDetailDocumentRepository extends ElasticsearchRepository<ProductDetailDocument, String> {
    List<ProductDetailDocument> findAllByCustomerIdx(Long idx);
}
