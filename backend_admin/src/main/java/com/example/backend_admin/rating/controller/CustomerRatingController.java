package com.example.backend_admin.rating.controller;


import com.example.backend_admin.rating.service.CustomerRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerRatingController {
    private final CustomerRatingService customerRatingService;

    @RequestMapping(method = RequestMethod.GET, value = "/rating")
    public ResponseEntity rating(){
        return ResponseEntity.ok().body(customerRatingService.rating());
    }
}
