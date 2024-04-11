package com.example.backend_admin.admin.controller;

import com.example.backend_admin.admin.model.request.PostAdminLoginReq;
import com.example.backend_admin.admin.model.request.PostAdminSignupReq;
import com.example.backend_admin.admin.service.AdminService;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
    private final AdminService adminService;
    private final LoginLogRespository loginLogRespository;
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup (@Valid @RequestBody PostAdminSignupReq postCustomerSignupReq){

        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(adminService.signup(postCustomerSignupReq)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody PostAdminLoginReq request){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(adminService.adminLogin(request)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

}
