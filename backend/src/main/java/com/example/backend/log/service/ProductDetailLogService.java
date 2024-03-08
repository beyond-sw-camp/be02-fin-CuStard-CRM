package com.example.backend.log.service;

import com.example.backend.customer.model.entity.Customer;
import com.example.backend.log.entity.LoginLog;
import com.example.backend.log.entity.ProductDetailLog;
import com.example.backend.log.repository.ProductDetailLogRespository;
import com.example.backend.product.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailLogService {
    private final ProductDetailLogRespository productDetailLogRespository;

    public void productDetailLogging(Customer customer, Product product){
        productDetailLogRespository.save(ProductDetailLog.builder()
                        .customer(customer)
                        .product(product)
                        .build());
    }
}
