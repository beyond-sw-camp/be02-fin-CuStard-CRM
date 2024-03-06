package com.example.custard.customer.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCustomerLoginReq {
    private String customerEmail;
    private String customerPwd;
}
