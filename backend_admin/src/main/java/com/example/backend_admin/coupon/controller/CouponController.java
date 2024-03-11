package com.example.backend_admin.coupon.controller;

import com.example.backend_admin.coupon.model.request.PostCouponCreateReq;
import com.example.backend_admin.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(@RequestBody PostCouponCreateReq postCouponCreateReq){
        return ResponseEntity.ok().body(couponService.create(postCouponCreateReq));
    }
    @RequestMapping(method = RequestMethod.GET,value = "/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx){
        return ResponseEntity.ok().body(couponService.read(idx));
    }
    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public ResponseEntity list(){
        return ResponseEntity.ok().body(couponService.list());
    }
}
