package com.example.backend_admin.calculate.controller;

import com.example.backend_admin.calculate.model.response.*;
import com.example.backend_admin.calculate.service.CalculateLogService;
import com.example.backend_admin.calculate.service.CalculateOrdersService;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.orders.model.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class CalculateController {
    private final CalculateLogService calculateLogService;
    private final CalculateOrdersService calculateOrdersService;

    @RequestMapping("/today/login")
    public ResponseEntity todayLogin(){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(calculateLogService.todayLogin()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    @RequestMapping("/today/signup")
    public ResponseEntity todaySignup(){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse( calculateLogService.todaySignUp()));
        }catch (BaseException exception){
           return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    @RequestMapping("/today/orders")
    public ResponseEntity todayOrders() {
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(calculateOrdersService.todayOrders()));
        } catch (BaseException exception) {
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }


    @RequestMapping("/category/orders")
    public ResponseEntity categoryOrdersRes(){

        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(calculateOrdersService.categoryOrderRes()));
        }catch (BaseException exception){
           return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    @RequestMapping("/month/orders")
    public ResponseEntity monthOrdersRes(){

        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(calculateOrdersService.monthOrdersRes()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }


    @RequestMapping("/today/sleep")
    public ResponseEntity sleepAccountGrowthRate(){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(calculateLogService.sleepAccountGrowthRate()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    @RequestMapping("/today/count")
    public ResponseEntity LoginTime(){
        try {
           return ResponseEntity.ok().body(BaseResponse.successResponse(calculateLogService.loginTime()));
        }catch (BaseException exception){
           return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    @RequestMapping("login/time/{idx}")
    public ResponseEntity customerLoginTime(@PathVariable Long idx){
        try {
           return ResponseEntity.ok().body(BaseResponse.successResponse(calculateLogService.customerLoginTime(idx)));
        }catch (BaseException exception){
           return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }
    @RequestMapping("/customer/orders/{idx}")
    public ResponseEntity customerOrdersRes(@PathVariable Long idx){
        try {
           return ResponseEntity.ok().body(BaseResponse.successResponse(calculateOrdersService.customerOrdersRes(idx)));
        }catch (BaseException exception){
           return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }


}
