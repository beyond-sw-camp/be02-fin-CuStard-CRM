package com.example.backend_admin.elastic.repository;

import com.example.backend_admin.elastic.entity.OrdersDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrdersDocumentRepository extends ElasticsearchRepository<OrdersDocument, String> {
}
