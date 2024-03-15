package com.example.backend.customer.model.response;

import com.example.backend.common.CustomerLevel;
import com.example.backend.havecoupon.model.response.GetHaveCouponBaseRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerReadRes {

    private Long idx;
    private String customerEmail;
    private String authority;
    private Integer totalAmount;
    private CustomerLevel level;

    List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();

}
