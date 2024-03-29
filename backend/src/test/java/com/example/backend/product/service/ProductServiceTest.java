package com.example.backend.product.service;

import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponseStatus;
import com.example.backend.product.model.entity.Product;
import com.example.backend.product.model.response.GetProductListRes;
import com.example.backend.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    public static Product product;
    @BeforeAll
    static void setUp(){
        product = Product.builder()
                .idx(1L)
                .productName("당근")
                .productPrice(3000)
                .productDetails("맛있는 당근")
                .ProductImage("test")
                .category(1)
                .build();
    }
    @Test
    void product_list_success() throws BaseException{
        //given
        List<Product> result = new ArrayList<>();
        result.add(product);
        given(productRepository.findAll()).willReturn(result);

        //when
        List<GetProductListRes> response = productService.list();

        //then
        assertNotNull(response);
        assertEquals(1, response.size());
        GetProductListRes getProductListRes = response.get(0);
        assertEquals(product.getIdx(), getProductListRes.getIdx());
        assertEquals(product.getProductName(), getProductListRes.getProductName());
        assertEquals(product.getProductPrice(), getProductListRes.getProductPrice());
        assertEquals(product.getProductImage(), getProductListRes.getProductImage());
        assertEquals(product.getCategory(), getProductListRes.getCategory());
    }
    @Test
    void product_list_fail() throws BaseException{
        //given
        List<Product> result = new ArrayList<>();
        given(productRepository.findAll()).willReturn(result);

        //when
        try {
            productService.list();
        }catch (BaseException e){
            throw new RuntimeException(e);
        }

        //then
        assertEquals(0, result.size());;
    }
}