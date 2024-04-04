package com.example.backend.customer.model.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCustomerRes {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private Map<String, String > result;

}
