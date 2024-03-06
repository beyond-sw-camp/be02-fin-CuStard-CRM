package com.example.custard.customer.model.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCustomerLoginRes {
    String accessToken;
    Long idx;
}
