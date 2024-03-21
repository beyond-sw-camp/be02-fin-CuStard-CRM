package com.example.backend_admin.customer.entity.response;

import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.havecoupon.model.entity.response.GetHaveCouponBaseRes;
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
    private String name;
    private String customerEmail;
    private String authority;
    private Integer totalAmount;
    private CustomerLevel level;
    private String lastLogin;

    List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();
}
