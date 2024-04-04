package com.example.backend_admin.customer.entity.response;

import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.havecoupon.model.entity.response.GetHaveCouponBaseRes;
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
    private String name;
    private String customerEmail;
    private String authority;
    private Integer totalAmount;
    private CustomerLevel level;
    private Integer age;
    private String address;
    private String gender;
    private String lastLogin;

    List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();

}
