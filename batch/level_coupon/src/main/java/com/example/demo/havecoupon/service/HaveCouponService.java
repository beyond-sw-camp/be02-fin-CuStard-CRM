package com.example.demo.havecoupon.service;

import com.example.demo.coupon.model.entity.Coupon;
import com.example.demo.customer.model.entity.Customer;
import com.example.demo.havecoupon.Repository.HaveCouponRepository;
import com.example.demo.havecoupon.model.entity.HaveCoupon;
import com.example.demo.havecoupon.model.entity.request.HaveCouponCreateReq;
import com.example.demo.havecoupon.model.entity.response.HaveCouponCreateRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HaveCouponService {
    private final HaveCouponRepository haveCouponRepository;

    public HaveCouponCreateRes create(HaveCouponCreateReq haveCouponCreateReq){
        HaveCoupon haveCoupon = haveCouponRepository.save(HaveCoupon.builder()
                .count(haveCouponCreateReq.getCount())
                .customer(Customer.builder()
                        .idx(haveCouponCreateReq.getCustomerIdx())
                        .build())
                .coupon(Coupon.builder()
                        .idx(haveCouponCreateReq.getCouponIdx())
                        .build())
                .build());

        return HaveCouponCreateRes.builder()
                .idx(haveCoupon.getIdx())
                .count(haveCoupon.getCount())
                .customerIdx(haveCouponCreateReq.getCustomerIdx())
                .couponIdx(haveCouponCreateReq.getCouponIdx())
                .build();
    }
}
