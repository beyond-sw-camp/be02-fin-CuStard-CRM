package com.example.backend.coupon.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponPushNoti {
    private String customerEmail;
}
