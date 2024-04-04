package com.example.backend_admin.orders.model.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPortoneRes {
    private Long id;
    private String name;
    private Integer price;
}
