package com.example.backend_admin.calculate.controller;

import com.example.backend_admin.calculate.model.response.GetTodayLoginRes;
import com.example.backend_admin.calculate.service.CalculateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class CalculateController {
    private final CalculateLogService calculateService;

    @RequestMapping("/today/login")
    public GetTodayLoginRes todayLogin(){
        return calculateService.todayLogin();
    }
}
