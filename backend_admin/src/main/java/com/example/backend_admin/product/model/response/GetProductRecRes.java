package com.example.backend_admin.product.model.response;


import com.example.backend_admin.product.model.entity.Product;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductRecRes {

    Map<String, Map<Product, Double>> recommendList;

}
