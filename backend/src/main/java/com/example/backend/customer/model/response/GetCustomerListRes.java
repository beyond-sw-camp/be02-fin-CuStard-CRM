package com.example.backend.customer.model.response;

import com.example.backend.havecoupon.model.response.GetHaveCouponBaseRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponListRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponReadRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCustomerListRes {
    private Long idx;
    private String customerEmail;
    private String authority;

    List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();
}
