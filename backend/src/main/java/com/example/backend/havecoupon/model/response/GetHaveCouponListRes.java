package com.example.backend.havecoupon.model.response;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetHaveCouponListRes {
    private Long idx;
    private Integer count;

    private Long customerIdx;
    private Long couponIdx;
}
