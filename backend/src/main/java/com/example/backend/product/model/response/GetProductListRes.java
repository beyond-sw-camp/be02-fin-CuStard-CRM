package com.example.backend.product.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetProductListRes {
    private Long idx;
    private Integer category;
    private String productName;
    private Integer productPrice;
    private String productImage;
}
