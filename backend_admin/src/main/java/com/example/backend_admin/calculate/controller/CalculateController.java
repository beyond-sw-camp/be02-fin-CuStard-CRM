package com.example.backend_admin.calculate.controller;

import com.example.backend_admin.calculate.model.response.*;
import com.example.backend_admin.calculate.service.CalculateLogService;
import com.example.backend_admin.calculate.service.CalculateOrdersService;
import com.example.backend_admin.log.entity.LoginLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public GetTodayLoginByThreeHour todayLoginByThreeHour(){
        return calculateLogService.todayLoginByThreeHour();
    }

    @GetMapping(value = "/today/time/{idx}")
    public List<List<Long>> findLoginTime(@PathVariable Long idx){
        return calculateLogService.findLoginTime(idx);
    }
}
