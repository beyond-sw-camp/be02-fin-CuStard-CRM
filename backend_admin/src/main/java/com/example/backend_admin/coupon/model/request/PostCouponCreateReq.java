package com.example.backend_admin.coupon.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCouponCreateReq<T> {
    private String selectedOption;
    private T dataToSend;
    private Integer discount;
    private Integer couponCategory;
}
