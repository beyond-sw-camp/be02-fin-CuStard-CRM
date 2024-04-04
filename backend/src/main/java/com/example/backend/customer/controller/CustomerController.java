package com.example.backend.customer.controller;

//import com.example.custard.member.model.entity.Manager;


import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import com.example.backend.common.BaseResponseStatus;
import com.example.backend.customer.model.request.GetEmailConfirmReq;
import com.example.backend.customer.model.request.PostCustomerLoginReq;
import com.example.backend.customer.model.request.PostCustomerSignupReq;
import com.example.backend.customer.model.response.GetCustomerConfirmRes;
import com.example.backend.customer.model.response.PostCustomerSignupRes;
import com.example.backend.customer.service.CustomerEmailVerifyService;
import com.example.backend.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import java.nio.file.AccessDeniedException;

import static com.example.backend.common.BaseResponseStatus.CUSTOMER_SIGNUP_EMPTY_EMAIL;
import static com.example.backend.common.BaseResponseStatus.CUSTOMER_SIGNUP_EMPTY_PASSWORD;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {

    private final CustomerEmailVerifyService customerEmailVerifyService;
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;


    @RequestMapping(method = RequestMethod.POST, value = "/customer/signup")
    public ResponseEntity signup (@Valid @RequestBody PostCustomerSignupReq postCustomerSignupReq) {

        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(customerService.signup(postCustomerSignupReq)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/customerconfirm")
    public RedirectView memberConfirm(GetEmailConfirmReq getEmailConfirmReq) {

        if (customerEmailVerifyService.confirm(getEmailConfirmReq.getCustomerEmail(), getEmailConfirmReq.getUuid())) {
            customerEmailVerifyService.update(getEmailConfirmReq.getCustomerEmail());

            return new RedirectView("http://192.168.0.31:80/emailconfirm/" + getEmailConfirmReq.getJwt());
        } else {

            return new RedirectView("http://192.168.0.31:80/emailCertError");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customer/login")
    public ResponseEntity login(@Valid @RequestBody PostCustomerLoginReq request){

        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(customerService.customerLogin(request)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer/list")
    public ResponseEntity list(){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(customerService.list()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/customer/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx){
        try {
            return  ResponseEntity.ok().body(BaseResponse.successResponse(customerService.read(idx)) );
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/delete")
    public ResponseEntity delete(@RequestHeader(value = "Authorization") String token){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(customerService.delete(token)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
}
