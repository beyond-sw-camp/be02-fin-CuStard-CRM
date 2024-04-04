package com.example.backend_admin.coupon.repository;

import com.example.backend_admin.coupon.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
