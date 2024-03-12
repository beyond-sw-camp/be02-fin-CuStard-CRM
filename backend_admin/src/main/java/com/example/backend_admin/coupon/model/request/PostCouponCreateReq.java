package com.example.backend_admin.coupon.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCouponCreateReq {
    private Long adminIdx;
    private Integer discount;
    private Integer couponCategory;
}
