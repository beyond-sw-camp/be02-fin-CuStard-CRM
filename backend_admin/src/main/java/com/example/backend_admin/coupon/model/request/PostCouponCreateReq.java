package com.example.backend_admin.coupon.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCouponCreateReq {
    private Long adminIdx;
    @NotBlank
    private Integer discount;
    @NotBlank
    private Integer couponCategory;
}
