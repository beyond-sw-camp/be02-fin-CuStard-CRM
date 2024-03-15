package com.example.backend_admin.admin.model.request;

import com.example.backend_admin.customer.entity.Customer;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostAdminLevelCouponReq {

    private List<List<Long>> targetList;
}
