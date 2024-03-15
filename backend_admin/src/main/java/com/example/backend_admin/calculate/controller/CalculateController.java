package com.example.backend_admin.calculate.controller;

import com.example.backend_admin.calculate.model.response.GetTodayLoginRes;
import com.example.backend_admin.calculate.model.response.GetTodayOrdersRes;
import com.example.backend_admin.calculate.model.response.GetTodaySignupRes;
import com.example.backend_admin.calculate.service.CalculateLogService;
import com.example.backend_admin.calculate.service.CalculateOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
