package com.example.backend_admin.log.repository;

import com.example.backend_admin.log.entity.ProductDetailLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailLogRespository extends JpaRepository<ProductDetailLog, Long> {
    List<ProductDetailLog> findByCustomerIdx(Long idx);
}
