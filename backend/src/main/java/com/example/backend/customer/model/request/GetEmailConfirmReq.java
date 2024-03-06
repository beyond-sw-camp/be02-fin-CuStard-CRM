package com.example.backend.customer.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetEmailConfirmReq {

    private String customerEmail;
    private String jwt;
    private String uuid;
}
