package com.example.backend.product.service;


import com.example.backend.product.model.entity.Product;
import com.example.backend.product.model.response.GetProductListRes;
import com.example.backend.product.model.response.GetProductRes;
import com.example.backend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<GetProductListRes> list() {
        List<GetProductListRes> productListRes = new ArrayList<>();

        List<Product> productList= productRepository.findAll();
        for(Product product : productList){
            productListRes.add(GetProductListRes.builder()
                    .idx(product.getIdx())
                    .category(product.getCategory())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .build());
        }

        return productListRes;
    }

    public GetProductRes read(Long idx) {
        Optional<Product> result = productRepository.findById(idx);

        if(result.isPresent()) {
            Product product = result.get();
            GetProductRes getProductRes = GetProductRes.builder()
                    .idx(product.getIdx())
                    .category(product.getCategory())
                    .productName(product.getProductName())
                    .productDetails(product.getProductDetails())
                    .productPrice(product.getProductPrice())
                    .build();

            return getProductRes;
        }
        return null;
    }

    public List<GetProductListRes> searchByName(String keyword) {
        List<Product> productList = productRepository.findByProductNameContaining(keyword);

        List<GetProductListRes> productListRes = new ArrayList<>();

        for(Product product : productList){
            productListRes.add(GetProductListRes.builder()
                    .idx(product.getIdx())
                    .category(product.getCategory())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .build());
        }

        return productListRes;
    }
}
