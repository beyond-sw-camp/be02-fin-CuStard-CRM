package com.example.custard.customer.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerReadRes {

    private String customerEmail;
    private String password;
    private String authority;

}
