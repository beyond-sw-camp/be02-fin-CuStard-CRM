package com.example.backend.customer.controller;

//import com.example.custard.member.model.entity.Manager;


import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import com.example.backend.common.BaseResponseStatus;
import com.example.backend.customer.model.request.GetEmailConfirmReq;
import com.example.backend.customer.model.request.PostCustomerLoginReq;
import com.example.backend.customer.model.request.PostCustomerSignupReq;
import com.example.backend.customer.model.response.PostCustomerSignupRes;
import com.example.backend.customer.service.CustomerEmailVerifyService;
import com.example.backend.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;
import javax.validation.Valid;

import static com.example.backend.common.BaseResponseStatus.*;



@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {

    private final CustomerEmailVerifyService customerEmailVerifyService;
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;


    @RequestMapping(method = RequestMethod.POST, value = "/customer/signup")
    public ResponseEntity signup (@Valid @RequestBody PostCustomerSignupReq postCustomerSignupReq){
        log.info("@ param : {}", postCustomerSignupReq.getCustomerEmail());
        if (postCustomerSignupReq.getCustomerEmail() == null){
            return ResponseEntity.ok().body(BaseResponse.failResponse(CUSTOMER_SIGNUP_EMPTY_EMAIL));
        }
        if (postCustomerSignupReq.getCustomerPwd() == null){
            return ResponseEntity.ok().body(BaseResponse.failResponse(CUSTOMER_SIGNUP_EMPTY_PASSWORD));
        }
        try {
            return ResponseEntity.ok().body(customerService.signup(postCustomerSignupReq));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/customerconfirm")
    public RedirectView memberConfirm(GetEmailConfirmReq getEmailConfirmReq) {

        if (customerEmailVerifyService.confirm(getEmailConfirmReq.getCustomerEmail(), getEmailConfirmReq.getUuid())) {
            customerEmailVerifyService.update(getEmailConfirmReq.getCustomerEmail());

            return new RedirectView("http://localhost:3000/emailconfirm/" + getEmailConfirmReq.getJwt());
        } else {

            return new RedirectView("http://localhost:3000/emailCertError");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customer/login")
    public ResponseEntity login(@Valid @RequestBody PostCustomerLoginReq request){
        if (request.getCustomerEmail() == null){
            return ResponseEntity.ok().body(BaseResponse.failResponse(CUSTOMER_SIGNUP_EMPTY_EMAIL));
        }
        if (request.getCustomerPwd() == null){
            return ResponseEntity.ok().body(BaseResponse.failResponse(CUSTOMER_SIGNUP_EMPTY_PASSWORD));
        }
        try {
            return ResponseEntity.ok().body(customerService.customerLogin(request));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer/list")
    public ResponseEntity list(){
        try {
            return ResponseEntity.ok().body(customerService.list());
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/customer/read/{idx}")
    public ResponseEntity read( @PathVariable Long idx){
        try {
            return  ResponseEntity.ok().body(customerService.read(idx));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/delete")
    public ResponseEntity delete(@RequestHeader(value = "Authorization") String token){
        try {
            return ResponseEntity.ok().body(customerService.delete(token));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
}
