//package com.example.backend_admin.dashboard.controller;
//
//import com.example.backend_admin.common.BaseResponse;
//import com.example.backend_admin.common.CustomerLevel;
//import com.example.backend_admin.customer.entity.Customer;
//import com.example.backend_admin.dashboard.model.response.GetCustomerListRes;
//import com.example.backend_admin.dashboard.service.DashboardService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin("*")
//@RequestMapping("/")
//public class DashboardController {
//    private final DashboardService dashboardService;
//
//    @RequestMapping(method = RequestMethod.GET, value = "/select/level/{level}")
//    public ResponseEntity selectByLevel(@PathVariable CustomerLevel level){
//        return ResponseEntity.ok().body(BaseResponse.successResponse(dashboardService.selectByLevel(level)));
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/select/amount")
//    public ResponseEntity selectByAmount(){
//        return ResponseEntity.ok().body(BaseResponse.successResponse(dashboardService.selectByAmount()));
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/dashboard/count/active/user")
//    public void count(){
//
//    }
//}
