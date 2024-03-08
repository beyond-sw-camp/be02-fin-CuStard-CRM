package com.example.backend.coupon.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCouponCreateRes {
    private Long idx;
    private Integer discount;
    private Integer couponCategory;


}
