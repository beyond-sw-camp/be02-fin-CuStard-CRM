package com.example.backend.product.controller;

import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.product.model.response.GetProductListRes;
import com.example.backend.product.model.response.GetProductRecRes;
import com.example.backend.product.model.response.GetProductRes;
import com.example.backend.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(productService.list()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity getProductsByCategory(@PathVariable Integer category, Integer page) {
        return ResponseEntity.ok(BaseResponse.successResponse(productService.getProductsByCategory(category, page)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx, Authentication authentication) {
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(productService.read(idx, authentication)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{keyword}")
    public ResponseEntity searchByName(@PathVariable String keyword,Authentication authentication){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(productService.searchByName(keyword, authentication)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus())));
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recommend")
    public ResponseEntity recommendItem(){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(productService.recommend()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus())));
        }
    }
}
