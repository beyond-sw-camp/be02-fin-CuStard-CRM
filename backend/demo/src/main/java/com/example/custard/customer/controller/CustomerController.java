package com.example.custard.customer.controller;

//import com.example.custard.member.model.entity.Manager;
import com.example.custard.customer.model.request.GetEmailConfirmReq;
//import com.example.custard.member.model.requestdto.PostManagerLoginReq;
import com.example.custard.customer.model.request.PostCustomerLoginReq;
import com.example.custard.customer.model.request.PostCustomerSignupReq;
//import com.example.custard.member.model.responsedto.PostManagerLoginRes;
import com.example.custard.customer.model.response.PostCustomerSignupRes;
//import com.example.custard.member.service.ManagerEmailVerifyService;
import com.example.custard.customer.service.CustomerEmailVerifyService;
import com.example.custard.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;



@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {

    private final CustomerEmailVerifyService customerEmailVerifyService;
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;


    @RequestMapping(method = RequestMethod.POST, value = "/customer/signup")
    public ResponseEntity signup (@RequestBody PostCustomerSignupReq postCustomerSignupReq){
        PostCustomerSignupRes response = customerService.signup(postCustomerSignupReq);

        return ResponseEntity.ok().body(response);
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
    public ResponseEntity login(@RequestBody PostCustomerLoginReq request){

        return ResponseEntity.ok().body(customerService.customerLogin(request));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customer/delete")
    public ResponseEntity delete(@RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok().body(customerService.delete(token));
    }
}
