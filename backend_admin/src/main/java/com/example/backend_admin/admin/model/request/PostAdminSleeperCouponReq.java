package com.example.backend_admin.admin.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostAdminSleeperCouponReq {
    private Integer discount;
    private Integer couponCategory;
    private Integer period;
    private Integer count;
}
