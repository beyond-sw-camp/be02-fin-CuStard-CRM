package com.example.backend_admin.log.repository;


import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.orders.model.entity.Orders;
import com.mysql.cj.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoginLogRespository extends JpaRepository<LoginLog, Long> {
    List<LoginLog> findByCustomerIdx(Long customerIdx);

    @Query(value = "SELECT COUNT(*) FROM LoginLog WHERE LoginLog.createdDate BETWEEN DATE_ADD(DATE(NOW()),INTERVAL -2 DAY) AND DATE_ADD(DATE(NOW()),INTERVAL -1 DAY)", nativeQuery = true)
    Long countDaliyActiveUser();

    @Query(value = "SELECT * FROM login_log WHERE login_log.created_date > DATE_SUB(NOW(), INTERVAL 10 SECOND) GROUP BY login_log.customer_idx" ,nativeQuery = true)
    List<LoginLog> findBySleeperCouponTargetList();
}
