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

import javax.validation.Valid;
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
    public ResponseEntity ordersCreate(@RequestHeader(value = "Authorization") String token,@Valid String impUid) throws IamportResponseException, IOException,BaseException {
        try {
            if (paymentService.paymentValidation(impUid)) {
                return ResponseEntity.ok().body(BaseResponse.successResponse(ordersService.createOrder(token, impUid)));
            }
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
        return null;
    }
}
