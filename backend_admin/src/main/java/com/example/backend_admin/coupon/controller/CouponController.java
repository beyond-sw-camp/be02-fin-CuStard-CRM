package com.example.backend_admin.coupon.controller;

import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.coupon.model.request.PostCouponCreateReq;
import com.example.backend_admin.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.backend_admin.common.BaseResponseStatus.*;

@RestController
@RequestMapping("/admin/coupon")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CouponController {
    private final CouponService couponService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(@RequestHeader(value = "Authorization") String token, @RequestBody PostCouponCreateReq postCouponCreateReq) {
        if (postCouponCreateReq.getCouponCategory() == null){
            return ResponseEntity.ok().body(BaseResponse.failResponse(COUPON_CREATE_EMPTY_CATEGORY));
        }
        if (postCouponCreateReq.getDiscount() == null){
            return ResponseEntity.ok().body(BaseResponse.failResponse(COUPON_CREATE_EMPTY_DISCOUNT));
        }
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(couponService.create(token, postCouponCreateReq)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }
    @RequestMapping(method = RequestMethod.GET,value = "/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx){
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(couponService.read(idx)));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse( exception.getBaseResponseStatus()));
        }

    }
    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public ResponseEntity list(){

        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(couponService.list()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }

    }
}
