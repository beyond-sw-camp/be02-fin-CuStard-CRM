package com.example.backend.havecoupon.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetHaveCouponBaseRes {
    private Long idx;
    private Integer count;
}
