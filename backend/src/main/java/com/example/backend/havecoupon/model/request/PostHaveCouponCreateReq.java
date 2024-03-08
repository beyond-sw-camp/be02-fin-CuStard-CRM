package com.example.backend.havecoupon.model.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostHaveCouponCreateReq {
    private Integer count;
    private Long customerIdx;
    private Long couponIdx;
}
