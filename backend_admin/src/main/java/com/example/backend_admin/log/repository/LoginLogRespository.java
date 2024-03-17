package com.example.backend_admin.log.repository;


import com.example.backend_admin.calculate.model.response.GetTodayLoginRes;
import com.example.backend_admin.log.entity.LoginLog;
import com.example.backend_admin.orders.model.entity.Orders;
import com.mysql.cj.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Query(value = "SELECT T.time_table, IFNULL(login_time_table.login_count, 0) AS login_count FROM (SELECT 0 AS time_table UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12 UNION SELECT 13 UNION SELECT 14 UNION SELECT 15 UNION SELECT 16 UNION SELECT 17 UNION SELECT 18 UNION SELECT 19 UNION SELECT 20 UNION SELECT 21 UNION SELECT 22 UNION SELECT 23) AS T LEFT JOIN" +
            " ( SELECT DATE_FORMAT(created_date ,'%H') AS login_time, IFNULL(COUNT(*) , 0) AS login_count FROM (SELECT * FROM login_log WHERE created_date BETWEEN DATE_SUB(CURRENT_DATE(), INTERVAL 1 DAY) AND DATE_SUB(CURRENT_DATE(), INTERVAL 0 DAY)) AS login_time_table GROUP BY DATE_FORMAT(created_date ,'%H')) AS login_time_table ON T.time_table = login_time_table.login_time", nativeQuery = true)
    List<List<Long>> findByLoginByThreeHour();

}
