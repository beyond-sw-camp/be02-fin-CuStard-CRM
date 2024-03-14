package com.example.backend_admin.batch.model.response;

import com.example.backend_admin.customer.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelCouponReaderRes {
    private List<Customer> customerList;
}
