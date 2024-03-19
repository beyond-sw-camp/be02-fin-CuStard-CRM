package com.example.backend.coupon.controller;

import com.example.backend.coupon.model.request.PostCouponCreateReq;
import com.example.backend.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CouponController {
    private final CouponService couponService;

    @RequestMapping(method = RequestMethod.GET, value = "/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx){
        return ResponseEntity.ok().body(couponService.read(idx));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list(){
        return ResponseEntity.ok().body(couponService.list());
    }
    public void creat(){

    }

}
