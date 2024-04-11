package com.example.backend_admin.elastic.controller;

import com.example.backend_admin.elastic.entity.LoginDocument;
import com.example.backend_admin.elastic.repository.LoginDocumentRepository;
import com.example.backend_admin.elastic.repository.OrdersDocumentRepository;
import com.example.backend_admin.elastic.service.LoginService;
import com.example.backend_admin.elastic.service.OrdersService;
import com.example.backend_admin.elastic.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class ElasticController {

    private final LoginService loginService;
    private final OrdersService ordersService;
    private final ProductDetailService productDetailService;

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

    @GetMapping("/cust/read/category/{idx}")
    public Object catergoryRead(@PathVariable Long idx) {
        return productDetailService.catergoryRead(idx);
    }
}