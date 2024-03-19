package com.example.backend.orders.controller;

import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import com.example.backend.orders.model.response.GetOrdersCreateRes;
import com.example.backend.orders.service.OrdersService;
import com.example.backend.orders.service.PaymentService;
import com.siot.IamportRestClient.exception.IamportResponseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

import static com.example.backend.common.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrdersController {
    private final OrdersService ordersService;
    private final PaymentService paymentService;

    @RequestMapping(method = RequestMethod.GET,value = "/validation")
    public ResponseEntity ordersCreate(@RequestHeader(value = "Authorization") String token, String impUid) throws IamportResponseException, IOException,BaseException {
            //TODO: 프론트 연결 후 유효성 검증 가능(주석 해제)
        if (token == null) {
            ResponseEntity.ok().body(BaseResponse.failResponse(ORDERS_VALIDATION_AUTHENTICATION_FAIL));
        }
        if (impUid == null) {
            ResponseEntity.ok().body(BaseResponse.failResponse(ORDERS_VALIDATION_EMPTY_IMPUID));
        }
        try {
            if (paymentService.paymentValidation(impUid)) {
                return ResponseEntity.ok().body(BaseResponse.successResponse(ordersService.createOrder(token, impUid)));
            }
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
        return ResponseEntity.ok().body(BaseResponse.failResponse(ORDERS_VALIDATION_INCORRECT_INFOMATION));

    }
}
