package com.example.backend.havecoupon.model.response;

import com.example.backend.customer.model.response.GetCustomerReadRes;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetHaveCouponReadRes {
    Long idx;
    Integer count;

    GetCustomerReadRes getCustomerReadRes;
}
