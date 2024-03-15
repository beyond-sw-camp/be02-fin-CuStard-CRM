package com.example.backend_admin.log.repository;


import com.example.backend_admin.calculate.model.response.GetTodayLoginRes;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.orders.model.entity.Orders;
import com.mysql.cj.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoginLogRespository extends JpaRepository<LoginLog, Long> {
    List<LoginLog> findByCustomerIdx(Long customerIdx);

    Long countByCreatedDateAfter(LocalDateTime createdDate);
}
