package com.example.backend.havecoupon.controller;


import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import com.example.backend.havecoupon.model.request.PostHaveCouponCreateReq;
import com.example.backend.havecoupon.service.HaveCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.example.backend.common.BaseResponseStatus.HAVE_COUPON_LIST_EMPTY_IDX;

@RestController
@RequestMapping("/have")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HaveCouponController {
    private final HaveCouponService haveCouponService;


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list(){
        try{
            return ResponseEntity.ok().body(BaseResponse.successResponse(haveCouponService.list()));
        }catch (BaseException exception){
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx){
        if (idx == null){
            return ResponseEntity.ok().body(BaseResponse.failResponse(HAVE_COUPON_LIST_EMPTY_IDX));
        }
        try {
            return  ResponseEntity.ok().body(BaseResponse.successResponse(haveCouponService.read(idx)));
        }catch (BaseException exception){
            return  ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }

}
