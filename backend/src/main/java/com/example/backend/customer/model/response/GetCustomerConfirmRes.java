package com.example.backend.customer.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class GetCustomerConfirmRes {
    private String customerEmail;
}
