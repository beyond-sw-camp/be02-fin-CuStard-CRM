package com.example.backend_admin.elastic.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCategoryOrdersRes {
    int[] orders;
    int[] productRead;
    int ordersCount;
    int ordersAmount;
}