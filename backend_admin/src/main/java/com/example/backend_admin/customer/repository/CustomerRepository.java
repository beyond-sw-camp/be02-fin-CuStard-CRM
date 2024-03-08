package com.example.backend_admin.customer.repository;

import com.example.backend_admin.customer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByCustomerEmail(String customerEmail);
    // Optional<Member> findByPassword(String password);
}
