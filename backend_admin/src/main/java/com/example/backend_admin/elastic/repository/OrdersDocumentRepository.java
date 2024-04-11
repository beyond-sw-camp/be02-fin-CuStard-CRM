package com.example.backend_admin.elastic.repository;

import com.example.backend_admin.elastic.entity.LoginDocument;
import com.example.backend_admin.elastic.entity.OrdersDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;
import java.util.List;

public interface OrdersDocumentRepository extends ElasticsearchRepository<OrdersDocument, String> {
    List<OrdersDocument> findAllByTimestampAfter(Date start);
    List<OrdersDocument> findAllByTimestampBetween(Date startOfDay, Date endOfDay);
    List<OrdersDocument> findAllByCustomerIdx(Long idx);
}
