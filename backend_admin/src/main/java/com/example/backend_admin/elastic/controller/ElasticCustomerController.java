package com.example.backend_admin.elastic.controller;

import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.elastic.model.entity.CustomerDocument;
import com.example.backend_admin.elastic.service.ElasticCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/es/customer")
public class ElasticCustomerController {
    private final ElasticCustomerService elasticCustomerService;

    //고객 대시보드 클릭했을 때 첫 페이지
    @GetMapping("/main/{page}")
    public BaseResponse dashboard(@PathVariable Integer page) throws IOException {
        return BaseResponse.successResponse(elasticCustomerService.dashboard(page));
    }

    //주문 금액 높은 순으로 정렬한 고객 대시보드
    @GetMapping("/amount/desc/{page}")
    public BaseResponse amtDescDashboard(@PathVariable Integer page) throws IOException {
        return BaseResponse.successResponse(elasticCustomerService.amtDescDashboard(page));
    }

    //주문 금액 낮은 순으로 정렬한 고객 대시보드
    @GetMapping("/amount/asc/{page}")
    public BaseResponse amtFilterAscDashboard(@PathVariable Integer page) throws IOException {
        return BaseResponse.successResponse(elasticCustomerService.amtAscDashboard(page));
    }

    //등급별로 필터링한 고객 대시보드
    @GetMapping("/level/{level}/{page}")
    public BaseResponse getFilteredCustomers(
            @PathVariable CustomerLevel level,
            @PathVariable int page) {
        System.out.println(level.ordinal());
        return BaseResponse.successResponse(elasticCustomerService.getCustomersByLevel(level.ordinal()+1, page));
    }
}
