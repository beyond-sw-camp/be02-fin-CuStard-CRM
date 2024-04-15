package com.example.demo.login.repository;


import com.example.demo.login.model.entity.LoginDocument;
import com.example.demo.orders.model.entity.OrdersDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;
import java.util.List;

public interface LoginDocumentRepository extends ElasticsearchRepository<LoginDocument, String> {
    // 오늘 방문자 수
    long countByTimestampBetween(Date startOfDay, Date endOfDay);
    List<LoginDocument> findByTimestampAfter(Date startOfDay);
    List<LoginDocument> findByCustomerIdxAndTimestampAfter(Long idx,Date startOfDay);

    List<LoginDocument> findAllByTimestampBetween(Date startOfDay, Date endOfDay);
    List<LoginDocument> findByTimestampBetween(Date startOfDay, Date endOfDay);
    List<LoginDocument> findAllByCustomerIdx(Long idx);

}