package com.example.backend.coupon.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCouponCreateReq {
    private Integer discount;
    private Integer couponCategory;
}
