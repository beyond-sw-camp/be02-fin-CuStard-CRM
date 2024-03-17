package com.example.backend.customer.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCustomerSignupReq {
    @Column(nullable = false,length = 25)
    @Size(min = 1, max = 25)
    private String customerEmail;
    private String customerPwd;
}
