package com.example.backend.customer.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCustomerLoginReq {
    @NotBlank
    private String customerEmail;
    @NotBlank
    private String customerPwd;
}
