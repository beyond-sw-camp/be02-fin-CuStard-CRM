package com.example.demo.havecoupon.model.entity.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HaveCouponCreateRes {
    private Long idx;
    private Integer count;

    private Long customerIdx;
    private Long couponIdx;
}
