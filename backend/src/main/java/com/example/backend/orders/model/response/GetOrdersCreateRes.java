package com.example.backend.orders.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetOrdersCreateRes {
    private String impUid;
    private String productName;
    private Integer price;
}
