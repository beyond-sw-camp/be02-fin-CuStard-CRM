package com.example.backend_admin.elastic.controller;

import com.example.backend_admin.elastic.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ElasticController {

    private final LoginService loginService;

    @GetMapping("/login/count")
    public long getLoginCount() {
        return loginService.countTodayLogins();
    }
}