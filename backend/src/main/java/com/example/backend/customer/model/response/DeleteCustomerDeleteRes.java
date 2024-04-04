package com.example.backend.customer.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteCustomerDeleteRes {
    private String customerEmail;
}
