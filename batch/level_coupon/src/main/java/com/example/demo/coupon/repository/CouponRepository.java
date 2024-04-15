package com.example.demo.coupon.repository;

import com.example.demo.coupon.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
