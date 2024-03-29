package com.example.backend_admin.customer.repository;

import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByCustomerEmail(String customerEmail);
    // Optional<Member> findByPassword(String password);
    List<Customer> findByLevel(CustomerLevel level);
    List<Customer> findAllByOrderByTotalAmountDesc();

    @Query(value = "SELECT customer.idx FROM customer WHERE customer.level = :levelNum", nativeQuery = true)
    List<Long> findCustomerIdxByLevel(Integer levelNum);
    Long countByCreatedDateAfter(LocalDateTime createdDate);
}
