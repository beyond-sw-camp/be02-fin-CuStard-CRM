package com.example.backend_admin.batch.model.request;

import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.log.entity.LoginLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SleeperCouponProcessorReq {
//    private List<Customer> customerList;
    private List<List<LoginLog>> loginLogLists;
}
