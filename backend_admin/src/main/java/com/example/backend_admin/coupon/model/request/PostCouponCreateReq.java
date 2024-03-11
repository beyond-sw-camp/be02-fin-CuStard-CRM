package com.example.backend_admin.coupon.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCouponCreateReq {
    private Long adminIdx;
    private Integer discount;
    private Integer couponCategory;
}
