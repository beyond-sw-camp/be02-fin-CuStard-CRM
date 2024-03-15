package com.example.backend_admin.orders.repository;

import org.springframework.data.jpa.repository.Query;
import com.example.backend_admin.orders.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerIdx(Long customerIdx);

    Integer countByCreatedDateAfter(LocalDateTime createdDate);
    @Query("SELECT COALESCE(SUM(o.productPrice), 0) FROM Orders o WHERE o.createdDate > :createdDate")
    Integer sumProductPriceByCreatedDateAfter(@Param("createdDate") LocalDateTime createdDate);
}
