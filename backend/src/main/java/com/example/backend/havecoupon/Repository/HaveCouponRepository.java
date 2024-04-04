package com.example.backend.havecoupon.Repository;

import com.example.backend.customer.model.entity.Customer;
import com.example.backend.havecoupon.model.entity.HaveCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HaveCouponRepository extends JpaRepository<HaveCoupon, Long> {
    Optional<HaveCoupon> findFirstByCustomerIdxOrderByCreatedDateDesc(Long customerIdx);
}
