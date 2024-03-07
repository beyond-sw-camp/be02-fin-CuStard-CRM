package com.example.backend.product.controller;

import com.example.backend.product.model.response.GetProductListRes;
import com.example.backend.product.model.response.GetProductRes;
import com.example.backend.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<List<GetProductListRes>> list() {
        return ResponseEntity.ok().body(productService.list());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity<GetProductRes> read(Long idx) {
        return ResponseEntity.ok().body(productService.read(idx));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<List<GetProductListRes>> searchByName(String keyword){
        return ResponseEntity.ok().body(productService.searchByName(keyword));
    }

}
