package com.example.demo.havecoupon.model.entity.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HaveCouponCreateReq {
    private Integer count;
    private Long customerIdx;
    private Long couponIdx;
}
