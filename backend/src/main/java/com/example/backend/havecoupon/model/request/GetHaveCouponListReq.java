package com.example.backend.havecoupon.model.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetHaveCouponListReq {
    Long idx;
    Integer count;
}
