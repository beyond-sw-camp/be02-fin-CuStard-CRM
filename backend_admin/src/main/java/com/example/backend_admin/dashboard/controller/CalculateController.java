package com.example.backend_admin.dashboard.controller;

import com.example.backend_admin.customer.entity.response.GetCustomerReadRes;
import com.example.backend_admin.customer.service.CustomerService;
import com.example.backend_admin.dashboard.model.dto.*;
import com.example.backend_admin.dashboard.model.response.CustomerDetailRes;
import com.example.backend_admin.dashboard.model.response.MainDashBoardRes;
import com.example.backend_admin.dashboard.service.CalculateLogService;
import com.example.backend_admin.dashboard.service.CalculateOrdersService;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor

@RequestMapping("/dashboard")
public class CalculateController {
    private final CalculateLogService calculateLogService;
    private final CalculateOrdersService calculateOrdersService;
    private final CustomerService customerService;

    @RequestMapping ("/main")
    public BaseResponse getMainDashboard(){
        try{
            GetTodayLoginRes getTodayLoginRes = calculateLogService.todayLogin();
            GetTodaySignupRes getTodaySignupRes = calculateLogService.todaySignUp();
            GetTodayOrdersRes getTodayOrdersRes = calculateOrdersService.todayOrders();
            GetCategoryOrdersRes getCategoryOrdersRes = calculateOrdersService.categoryOrderRes();
            GetCategoryOrdersRes getMonthOrdersRes = calculateOrdersService.monthOrdersRes();
            GetSleepAccountRes getSleepAccountRes = calculateLogService.sleepAccountGrowthRate();
            GetLoginTimeRes getLoginTimeRes = calculateLogService.loginTime();

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

    @RequestMapping("customer/detail/{idx}")
    public BaseResponse getCustomerDetail(@PathVariable Long idx) {
        try {
            GetLoginTimeRes getLoginTimeRes = calculateLogService.customerLoginTime(idx);
            GetCategoryOrdersRes getCategoryOrdersRes = calculateOrdersService.customerOrdersRes(idx);
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

//    밑에는 수정 전 코드


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

//    @RequestMapping("/today/sleep")
//    public ResponseEntity sleepAccountGrowthRate(){
//        try {
//            return ResponseEntity.ok().body(BaseResponse.successResponse(calculateLogService.sleepAccountGrowthRate()));
//        }catch (BaseException exception){
//            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
//        }
//
//    }

    @RequestMapping("/today/count")
    public ResponseEntity LoginTime(){
        try {
           return ResponseEntity.ok().body(BaseResponse.successResponse(calculateLogService.loginTime()));
        }catch (BaseException exception){
           return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }

//    @RequestMapping("login/time/{idx}")
//    public ResponseEntity customerLoginTime(@PathVariable Long idx){
//        try {
//           return ResponseEntity.ok().body(BaseResponse.successResponse(calculateLogService.customerLoginTime(idx)));
//        }catch (BaseException exception){
//           return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
//        }
//
//    }
//    @RequestMapping("/customer/orders/{idx}")
//    public ResponseEntity customerOrdersRes(@PathVariable Long idx){
//        try {
//           return ResponseEntity.ok().body(BaseResponse.successResponse(calculateOrdersService.customerOrdersRes(idx)));
//        }catch (BaseException exception){
//           return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
//        }
//
//    }

}
