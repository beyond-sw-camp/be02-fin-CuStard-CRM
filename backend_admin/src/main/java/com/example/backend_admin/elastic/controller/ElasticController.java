package com.example.backend_admin.elastic.controller;

import com.example.backend_admin.customer.service.CustomerService;
import com.example.backend_admin.elastic.model.response.*;
import com.example.backend_admin.customer.entity.response.GetCustomerReadRes;
import com.example.backend_admin.elastic.model.dto.*;
import com.example.backend_admin.dashboard.model.dto.GetTodaySignupRes;
import  com.example.backend_admin.dashboard.model.dto.GetSleepAccountRes;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.dashboard.service.CalculateLogService;
import com.example.backend_admin.elastic.model.dto.GetTodayLoginRes;
import com.example.backend_admin.elastic.model.response.MainDashBoardRes;
import com.example.backend_admin.elastic.service.LoginService;
import com.example.backend_admin.elastic.service.OrdersService;
//import com.example.backend_admin.elastic.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/es/dashboard")
public class ElasticController {

    private final LoginService loginService;
    private final OrdersService ordersService;
//    private final ProductDetailService productDetailService;

    private final CalculateLogService calculateLogService;
    private final CustomerService customerService;

    //메인 대시보드 요청
    @RequestMapping ("/main")
    public BaseResponse getMainDashboard(){
        try{
            GetTodayLoginRes getTodayLoginRes = loginService.countTodayLogins();
            GetTodaySignupRes getTodaySignupRes = calculateLogService.todaySignUp();
            GetTodayOrdersRes getTodayOrdersRes = ordersService.countTodayOrders();
            GetCategoryOrdersRes getCategoryOrdersRes = ordersService.catergorySales();
            GetCategoryOrdersRes getMonthOrdersRes = ordersService.monthlySales();
            GetSleepAccountRes getSleepAccountRes = calculateLogService.sleepAccountGrowthRate();
            GetLoginTimeRes getLoginTimeRes = loginService.countLoginTime();

            return BaseResponse.successResponse(MainDashBoardRes.builder()
                    .getTodayLoginRes(getTodayLoginRes)
                    .getTodaySignupRes(getTodaySignupRes)
                    .getTodayOrdersRes(getTodayOrdersRes)
                    .getCategoryOrdersRes(getCategoryOrdersRes)
                    .getMonthOrdersRes(getMonthOrdersRes)
                    .getSleepAccountRes(getSleepAccountRes)
                    .getLoginTimeRes(getLoginTimeRes)
                    .build());

        } catch (BaseException exception){
            return BaseResponse.failResponse(exception.getBaseResponseStatus());
        }
    }

    //고객 대시보드 요청
    @RequestMapping("customer/detail/{idx}")
    public BaseResponse getCustomerDetail(@PathVariable Long idx) {
        try {
            GetLoginTimeRes getLoginTimeRes = loginService.custLoginTime(idx);
            GetCategoryOrdersRes getCategoryOrdersRes = ordersService.custCatergoryOrders(idx);
//            GetCustomerReadRes getCustomerReadRes = productDetailService.catergoryRead(idx);
            GetCustomerReadRes getCustomerReadRes = customerService.read(idx);

            return  BaseResponse.successResponse(CustomerDetailRes.builder()
                    .getLoginTimeRes(getLoginTimeRes)
                    .getCategoryOrdersRes(getCategoryOrdersRes)
                    .getCustomerReadRes(getCustomerReadRes)
                    .build());

        } catch (BaseException exception){
            return BaseResponse.failResponse(exception.getBaseResponseStatus());
        }
    }
//======================테스트용 요청===============================
    @GetMapping("/login/count")
    public Object getLoginCount()  {
        return loginService.countTodayLogins();
    }

    @GetMapping("/orders/count")
    public Object getOrdersCount()  {
        return ordersService.countTodayOrders();
    }


//    =====하단 집계함수====
    @GetMapping("/orders/month")
    public Object monthlySales() {
        return ordersService.monthlySales();
    }

    @GetMapping("/login/time")
    public Object countLoginTime()  {
        return loginService.countLoginTime();
    }

    @GetMapping("/orders/category")
    public Object catergory() {
        return ordersService.catergorySales();
    }

    //====고객 집계함수=====
    @GetMapping("/cust/login/time/{idx}")
    public Object custLoginTime(@PathVariable Long idx)  {
        return loginService.custLoginTime(idx);
    }

    @GetMapping("/cust/orders/category/{idx}")
    public Object custCatergoryOrders(@PathVariable Long idx) {
        return ordersService.custCatergoryOrders(idx);
    }

//    @GetMapping("/cust/read/category/{idx}")
//    public Object catergoryRead(@PathVariable Long idx) {
//        return productDetailService.catergoryRead(idx);
//    }
    @GetMapping("/relogin")
    public int reLogin(){
        return loginService.reLogin();
    }
}