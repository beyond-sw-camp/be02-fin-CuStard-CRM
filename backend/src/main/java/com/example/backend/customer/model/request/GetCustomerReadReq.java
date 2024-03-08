package com.example.backend.customer.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCustomerReadReq {
    private String customerEmail;
}
