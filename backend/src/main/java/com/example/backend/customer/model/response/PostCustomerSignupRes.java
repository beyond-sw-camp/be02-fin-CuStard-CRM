package com.example.backend.customer.model.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCustomerSignupRes {
    private Long idx;
    private String customerEmail;

}
