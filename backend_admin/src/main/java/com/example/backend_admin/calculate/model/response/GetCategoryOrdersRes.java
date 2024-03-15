package com.example.backend_admin.calculate.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCategoryOrdersRes {
    int[] array;
}