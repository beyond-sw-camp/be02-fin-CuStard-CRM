package com.example.backend.orders.model.entity;

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
