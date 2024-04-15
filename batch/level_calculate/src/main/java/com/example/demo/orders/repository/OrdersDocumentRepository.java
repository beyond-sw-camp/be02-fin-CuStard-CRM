package com.example.demo.orders.repository;

import com.example.demo.orders.model.entity.OrdersDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;
import java.util.List;

public interface OrdersDocumentRepository extends ElasticsearchRepository<OrdersDocument, String> {
    List<OrdersDocument> findAllByTimestampAfter(Date start);
    List<OrdersDocument> findAllByTimestampBetween(Date startOfDay, Date endOfDay);
    List<OrdersDocument> findAllByCustomerIdx(Long idx);


}
