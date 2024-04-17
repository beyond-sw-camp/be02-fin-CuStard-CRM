package com.example.demo.havecoupon.model.entity.request;

import com.example.demo.coupon.model.entity.Coupon;
import com.example.demo.customer.model.entity.Customer;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HaveCouponCreateReq {
    private Integer count;
    private Customer customer;
    private Coupon coupon;
}
