package com.example.backend_admin.email.controller;

import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.email.service.EmailService;
import com.example.backend_admin.product.model.response.GetProductRecRes;
import com.example.backend_admin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/admin/ad")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmailController {
    private final EmailService emailService;
    private final ProductService productService;

    @RequestMapping(method = RequestMethod.POST, value = "/email")
    public ResponseEntity testEmail() throws MessagingException {

        emailService.sendEmails();
        return ResponseEntity.ok().body(BaseResponse.successResponse("전송"));
    }
}
