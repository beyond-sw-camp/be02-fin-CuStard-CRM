package com.example.backend.orders.controller;

import com.example.backend.orders.model.response.GetOrdersCreateRes;
import com.example.backend.orders.service.OrdersService;
import com.example.backend.orders.service.PaymentService;
import com.siot.IamportRestClient.exception.IamportResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrdersController {
    private final OrdersService ordersService;
    private final PaymentService paymentService;

    @RequestMapping(method = RequestMethod.GET,value = "/validation")
    public ResponseEntity<GetOrdersCreateRes> ordersCreate(@RequestHeader(value = "Authorization") String token, String impUid) throws IamportResponseException, IOException {
            //TODO: 프론트 연결 후 유효성 검증 가능(주석 해제)
            if(paymentService.paymentValidation(impUid)){
                return ordersService.createOrder(token, impUid);
            }
            return null;

    }
}
