package com.example.backend_admin.havecoupon.model.entity.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostHaveCouponCreateRes {
    private Long idx;
    private Integer count;

    private Long customerIdx;
    private Long couponIdx;
}
