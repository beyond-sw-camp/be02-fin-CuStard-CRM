package com.example.backend.havecoupon.model.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostHaveCouponCreateRes {
    Long idx;
    Integer count;
}
