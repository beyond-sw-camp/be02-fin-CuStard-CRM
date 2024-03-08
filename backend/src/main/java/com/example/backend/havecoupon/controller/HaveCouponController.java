package com.example.backend.havecoupon.controller;


import com.example.backend.havecoupon.model.request.PostHaveCouponCreateReq;
import com.example.backend.havecoupon.service.HaveCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/have")
@RequiredArgsConstructor
public class HaveCouponController {
    private final HaveCouponService haveCouponService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(@RequestBody PostHaveCouponCreateReq postHaveCouponCreateReq){

        return ResponseEntity.ok().body(haveCouponService.create(postHaveCouponCreateReq));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list(){
        return ResponseEntity.ok().body(haveCouponService.list());
    }
    @RequestMapping(method = RequestMethod.GET, value = "/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx){
        return  ResponseEntity.ok().body(haveCouponService.read(idx));
    }
    public void delete(){

    }
}
