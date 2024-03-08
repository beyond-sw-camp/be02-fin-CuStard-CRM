package com.example.backend.havecoupon.model.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetHaveCouponListReq {
    private Long idx;
    private Integer count;
}
