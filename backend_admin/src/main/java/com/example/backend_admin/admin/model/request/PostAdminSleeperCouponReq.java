package com.example.backend_admin.admin.model.request;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostAdminSleeperCouponReq {
    private List<Long> targetList;
}
