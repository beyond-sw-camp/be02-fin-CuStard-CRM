package com.example.backend_admin.elastic.repository;

import com.example.backend_admin.elastic.entity.ProductDetailDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductDetailDocumentRepository extends ElasticsearchRepository<ProductDetailDocument, String> {
}
