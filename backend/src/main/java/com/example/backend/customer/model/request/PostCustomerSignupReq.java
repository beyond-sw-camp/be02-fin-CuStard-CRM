package com.example.backend.customer.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCustomerSignupReq {

    private String customerEmail;
    private String customerPwd;
}
