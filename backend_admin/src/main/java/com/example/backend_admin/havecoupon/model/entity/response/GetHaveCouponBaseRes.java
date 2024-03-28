package com.example.backend_admin.havecoupon.model.entity.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetHaveCouponBaseRes {
    private Long idx;
    private Integer count;
}
