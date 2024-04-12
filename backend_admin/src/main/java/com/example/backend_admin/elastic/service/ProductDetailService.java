//package com.example.backend_admin.elastic.service;
//
//import com.example.backend_admin.customer.entity.response.GetCustomerReadRes;
//import com.example.backend_admin.elastic.entity.OrdersDocument;
//import com.example.backend_admin.elastic.entity.ProductDetailDocument;
//import com.example.backend_admin.elastic.repository.ProductDetailDocumentRepository;
//import com.example.backend_admin.product.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ProductDetailService {
//    private final ProductDetailDocumentRepository productDetailDocumentRepository;
//
//    public GetCustomerReadRes catergoryRead(Long idx) {
//        List<ProductDetailDocument> list = productDetailDocumentRepository.findAllByCustomerIdx(idx);
//
//        int[] categoryAmt = new int[5];
//        for(ProductDetailDocument product : list) {
//            categoryAmt[product.getCategory()-1]++;
//        }
//
//        System.out.println( " 카테고리별 조회: [" + categoryAmt[0] + "," + categoryAmt[1] + "," + categoryAmt[2] + ","  +categoryAmt[3] + "," +
//                categoryAmt[4] + "]");
//
//        return GetCustomerReadRes.builder()
//                .build();
//    }
//}
