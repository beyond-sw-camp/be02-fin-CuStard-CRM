package com.example.backend.coupon.model.response;

import com.example.backend.havecoupon.model.response.GetHaveCouponBaseRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponListRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponReadRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCouponListRes {

    private Long idx;
    private Integer discount;
    private Integer couponCategory;

    List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();
}
