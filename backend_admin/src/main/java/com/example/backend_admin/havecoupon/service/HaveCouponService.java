package com.example.backend_admin.havecoupon.service;

import com.example.backend_admin.coupon.model.entity.Coupon;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.havecoupon.Repository.HaveCouponRepository;
import com.example.backend_admin.havecoupon.model.entity.HaveCoupon;
import com.example.backend_admin.havecoupon.model.entity.request.PostHaveCouponCreateReq;
import com.example.backend_admin.havecoupon.model.entity.response.PostHaveCouponCreateRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HaveCouponService {
    private final HaveCouponRepository haveCouponRepository;

    public PostHaveCouponCreateRes create(PostHaveCouponCreateReq postHaveCouponCreateReq){
        HaveCoupon haveCoupon = haveCouponRepository.save(HaveCoupon.builder()
                .count(postHaveCouponCreateReq.getCount())
                .customer(Customer.builder()
                        .idx(postHaveCouponCreateReq.getCustomerIdx())
                        .build())
                .coupon(Coupon.builder()
                        .idx(postHaveCouponCreateReq.getCouponIdx())
                        .build())
                .build());

        return PostHaveCouponCreateRes.builder()
                .idx(haveCoupon.getIdx())
                .count(haveCoupon.getCount())
                .customerIdx(postHaveCouponCreateReq.getCustomerIdx())
                .couponIdx(postHaveCouponCreateReq.getCouponIdx())
                .build();
    }
}
