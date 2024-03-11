package com.example.backend.product.service;


import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.repository.CustomerRepository;
import com.example.backend.log.service.ProductDetailLogService;
import com.example.backend.log.service.SearchLogService;
import com.example.backend.product.model.entity.Product;
import com.example.backend.product.model.response.GetProductListRes;
import com.example.backend.product.model.response.GetProductRes;
import com.example.backend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailLogService productDetailLogService;
    private final CustomerRepository customerRepository;
    private final SearchLogService searchLogService;

    public List<GetProductListRes> list() {
        List<GetProductListRes> productListRes = new ArrayList<>();

        List<Product> productList= productRepository.findAll();
        for(Product product : productList){
            productListRes.add(GetProductListRes.builder()
                    .idx(product.getIdx())
                    .category(product.getCategory())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productImage(product.getProductImage())
                    .build());
        }

        return productListRes;
    }

    public GetProductRes read(Long idx, Authentication authentication) {
        Optional<Product> result = productRepository.findById(idx);

        if(result.isPresent()) {
            Product product = result.get();
            GetProductRes getProductRes = GetProductRes.builder()
                    .idx(product.getIdx())
                    .category(product.getCategory())
                    .productName(product.getProductName())
                    .productDetails(product.getProductDetails())
                    .productPrice(product.getProductPrice())
                    .productImage(product.getProductImage())
                    .build();

            if(authentication != null && authentication.isAuthenticated()) {
                Customer customer = (Customer)authentication.getPrincipal();
                Optional<Customer> c = customerRepository.findById(customer.getIdx());
                if(c.isPresent()) {
                    productDetailLogService.productDetailLogging(c.get(), product);
                }
            }
            return getProductRes;
        }
        return null;
    }

    public List<GetProductListRes> searchByName(String keyword, Authentication authentication) {
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

        if(authentication != null && authentication.isAuthenticated()) {
            Customer customer = (Customer)authentication.getPrincipal();
            Optional<Customer> c = customerRepository.findById(customer.getIdx());
            if(c.isPresent()) {
                searchLogService.SearchLogging(customer, keyword);
            }
        }
        return productListRes;
    }
}
