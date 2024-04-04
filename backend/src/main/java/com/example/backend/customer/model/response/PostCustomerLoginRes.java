package com.example.backend.customer.model.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCustomerLoginRes {
    private String accessToken;
    private Long idx;
}
