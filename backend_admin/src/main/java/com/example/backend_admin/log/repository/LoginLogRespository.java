package com.example.backend_admin.log.repository;


import com.example.backend_admin.log.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LoginLogRespository extends JpaRepository<LoginLog, Long> {
    Long countByCreatedDateAfter(LocalDateTime createdDate);

    List<LoginLog> findByCustomerIdx(Long customerIdx);

    @Query(value = "SELECT COUNT(*) FROM LoginLog WHERE LoginLog.createdDate BETWEEN DATE_ADD(DATE(NOW()),INTERVAL -2 DAY) AND DATE_ADD(DATE(NOW()),INTERVAL -1 DAY)", nativeQuery = true)
    Long countDaliyActiveUser();

    @Query(value = "SELECT login_log.customer_idx FROM login_log WHERE login_log.created_date < DATE_SUB(NOW(), INTERVAL 10 SECOND) GROUP BY login_log.customer_idx" ,nativeQuery = true)
    List<Long> findBySleeperCouponTargetList();

    @Query(value = "SELECT customer.idx FROM customer WHERE customer.level = 0",nativeQuery = true)
    List<Long> findByNewbieList();

    @Query(value = "SELECT customer.idx FROM customer WHERE customer.level = 1",nativeQuery = true)
    List<Long> findByBronzeList();

    @Query(value = "SELECT customer.idx FROM customer WHERE customer.level = 2",nativeQuery = true)
    List<Long> findBySilverList();

    @Query(value = "SELECT customer.idx FROM customer WHERE customer.level = 3",nativeQuery = true)
    List<Long> findByGoldList();

    @Query(value = "SELECT customer.idx FROM customer WHERE customer.level = 4",nativeQuery = true)
    List<Long> findByPlatinumList();

    @Query(value = "SELECT customer.idx FROM customer WHERE customer.level = 5",nativeQuery = true)
    List<Long> findByDiamondList();

    @Query(value = "SELECT  COUNT(DISTINCT customer_idx) AS two_days_ago FROM login_log WHERE login_log.created_date BETWEEN DATE_SUB(CURRENT_DATE(), INTERVAL 91 DAY) AND DATE_SUB(CURRENT_DATE(), INTERVAL 1 DAY)", nativeQuery = true)
    Long findByOneDaysAgoActiveCount();

    @Query(value = "SELECT  COUNT(DISTINCT customer_idx) AS one_days_ago FROM login_log WHERE login_log.created_date BETWEEN DATE_SUB(CURRENT_DATE(), INTERVAL 90 DAY) AND CURRENT_DATE()", nativeQuery = true)
    Long findByTodaysAgoActiveCount();

    @Query(value = "SELECT COUNT(*) FROM customer WHERE customer.created_date < DATE_SUB(CURRENT_DATE(), INTERVAL 1 DAY)", nativeQuery = true)
    Long findByAcountCountDayAgo();
    @Query(value = "SELECT COUNT(*) FROM customer WHERE customer.created_date < CURRENT_DATE()", nativeQuery = true)
    Long findByAcountCountToday();


    List<LoginLog> findByCreatedDateAfter(LocalDateTime createdDate);

    List<LoginLog> findByCustomerIdxAndCreatedDateAfter(Long CustomerIdx,LocalDateTime createdDate);
}
