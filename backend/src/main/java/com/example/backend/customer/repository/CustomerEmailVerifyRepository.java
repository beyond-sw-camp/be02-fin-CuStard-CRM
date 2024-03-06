package com.example.backend.customer.repository;


import com.example.backend.customer.model.entity.CustomerEmailVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerEmailVerifyRepository extends JpaRepository<CustomerEmailVerify, Long> {
    Optional<CustomerEmailVerify> findByCustomerEmail(String customerEmail);
}
