package com.example.backend_admin.elastic.repository;

import com.example.backend_admin.elastic.entity.LoginDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;

public interface LoginDocumentRepository extends ElasticsearchRepository<LoginDocument, String> {
    // 오늘 방문자 수
    long countByTimestampBetween(Date startOfDay, Date endOfDay);
}