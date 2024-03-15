package com.example.backend_admin.admin.controller;

import com.example.backend_admin.admin.model.request.PostAdminLevelCouponReq;
import com.example.backend_admin.admin.model.request.PostAdminLoginReq;
import com.example.backend_admin.admin.model.request.PostAdminSignupReq;
import com.example.backend_admin.admin.model.request.PostAdminSleeperCouponReq;
import com.example.backend_admin.admin.service.AdminService;
import com.example.backend_admin.log.repository.LoginLogRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
    private final AdminService adminService;
    private final LoginLogRespository loginLogRespository;
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity signup (@RequestBody PostAdminSignupReq postCustomerSignupReq){


        return ResponseEntity.ok().body(adminService.signup(postCustomerSignupReq));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody PostAdminLoginReq request){

        return ResponseEntity.ok().body(adminService.adminLogin(request));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/level/coupon")
    public ResponseEntity levelCoupon(@RequestHeader(value = "Authorization") String token,@RequestBody PostAdminLevelCouponReq postAdminLevelCouponReq){

        return ResponseEntity.ok().body(adminService.levelCoupon(postAdminLevelCouponReq));
    }
    @RequestMapping(method = RequestMethod.POST, value = "/sleeper/coupon")
    public ResponseEntity sleeperCoupon(@RequestHeader(value = "Authorization") String token, @RequestBody PostAdminSleeperCouponReq postAdminSleeperCouponReq){
        return ResponseEntity.ok().body(adminService.sleeperCoupon(postAdminSleeperCouponReq));
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//        LocalDateTime endDateTime = LocalDateTime.of(2024, 3, 13, 15, 30, 0);
//        Duration duration = Duration.between(endDateTime, now);
//        System.out.println(duration.getSeconds());
//        System.out.println(duration.getSeconds()/(3600*24L));
////        System.out.println(duration);
////        List<LoginLog> loginLogList = loginLogRespository.findByCustomerIdx(1L);
//
//
////        System.out.println(loginLogList.get(loginLogList.size()-1).getCreatedDate());
////        LocalDateTime lastLogin = loginLogList.get(loginLogList.size()-1).getCreatedDate();
//
////        System.out.println(now);
    }
}
