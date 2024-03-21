package com.example.backend_admin.customer.controller;

import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomController {
    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/customer/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx){
        try {
            return  ResponseEntity.ok().body(BaseResponse.successResponse(customerService.read(idx)) );
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/admin/customer/list")
    public ResponseEntity list(){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(customerService.list()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
}
