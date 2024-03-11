package com.example.backend_admin.dashboard.model.response;

import com.example.backend_admin.common.CustomerLevel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetCustomerListRes {
    private Long idx;
    private String customerEmail;
    private CustomerLevel level;
    private Integer totalAmount;
}
