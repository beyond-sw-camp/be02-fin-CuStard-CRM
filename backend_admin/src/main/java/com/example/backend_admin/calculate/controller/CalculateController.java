package com.example.backend_admin.calculate.controller;

import com.example.backend_admin.calculate.model.response.*;
import com.example.backend_admin.calculate.service.CalculateLogService;
import com.example.backend_admin.calculate.service.CalculateOrdersService;
import com.example.backend_admin.orders.model.entity.Orders;
import lombok.RequiredArgsConstructor;
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
    public GetTodayLoginRes todayLogin(){
        return calculateLogService.todayLogin();
    }

    @RequestMapping("/today/signup")
    public GetTodaySignupRes todaySignup(){
        return calculateLogService.todaySignUp();
    }

    @RequestMapping("/today/orders")
    public GetTodayOrdersRes todayOrders(){return calculateOrdersService.todayOrders();}


    @RequestMapping("/category/orders")
    public GetCategoryOrdersRes categoryOrdersRes(){
        return calculateOrdersService.categoryOrderRes();
    }

    @RequestMapping("/month/orders")
    public GetCategoryOrdersRes monthOrdersRes(){
        return calculateOrdersService.monthOrdersRes();
    }


    @RequestMapping("/today/sleep")
    public GetSleepAccountGrowthRateRes sleepAccountGrowthRate(){
        return calculateLogService.sleepAccountGrowthRate();
    }

    @RequestMapping("/today/count")
    public GetLoginTimeRes LoginTime(){
        return calculateLogService.loginTime();
    }

    @RequestMapping("login/time/{idx}")
    public GetLoginTimeRes customerLoginTime(@PathVariable Long idx){
        return calculateLogService.customerLoginTime(idx);
    }
    @RequestMapping("/customer/orders/{idx}")
    public GetCategoryOrdersRes customerOrdersRes(@PathVariable Long idx){
        return calculateOrdersService.customerOrdersRes(idx);
    }


}
