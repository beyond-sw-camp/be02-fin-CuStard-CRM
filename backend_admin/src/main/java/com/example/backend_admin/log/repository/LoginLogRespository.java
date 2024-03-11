package com.example.backend_admin.log.repository;


import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.orders.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginLogRespository extends JpaRepository<LoginLog, Long> {
    List<LoginLog> findByCustomerIdx(Long customerIdx);
}
