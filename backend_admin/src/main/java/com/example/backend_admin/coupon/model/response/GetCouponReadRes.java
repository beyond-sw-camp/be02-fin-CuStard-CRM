package com.example.backend_admin.coupon.model.response;

import com.example.backend_admin.admin.model.response.GetAdminReadRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCouponReadRes {
    private Long idx;
    private Integer discount;
    private Integer couponCategory;

    GetAdminReadRes getAdminReadRes;
}
