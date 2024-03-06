package com.example.custard.customer.model.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCustomerSignupRes {
    private Boolean isSuccess;
    private Long code;
    private String message;
    private Map<String, String > result;

}
