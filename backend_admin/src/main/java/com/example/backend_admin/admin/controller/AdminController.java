package com.example.backend_admin.admin.controller;

import com.example.backend_admin.admin.model.request.PostAdminLoginReq;
import com.example.backend_admin.admin.model.request.PostAdminSignupReq;
import com.example.backend_admin.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
    private final AdminService adminService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup (@RequestBody PostAdminSignupReq postCustomerSignupReq){


        return ResponseEntity.ok().body(adminService.signup(postCustomerSignupReq));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody PostAdminLoginReq request){

        return ResponseEntity.ok().body(adminService.adminLogin(request));
    }
}
