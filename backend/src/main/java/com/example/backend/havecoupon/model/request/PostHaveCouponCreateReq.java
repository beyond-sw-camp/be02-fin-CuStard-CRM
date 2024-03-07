package com.example.backend.havecoupon.model.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostHaveCouponCreateReq {
    Integer count;
    Long customerIdx;
}
