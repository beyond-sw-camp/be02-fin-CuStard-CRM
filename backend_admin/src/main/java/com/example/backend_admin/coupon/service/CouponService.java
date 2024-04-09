package com.example.backend_admin.coupon.service;


import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.admin.model.response.GetAdminReadRes;
import com.example.backend_admin.admin.repository.AdminRepository;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.coupon.model.entity.Coupon;
import com.example.backend_admin.coupon.model.request.PostCouponCreateReq;
import com.example.backend_admin.coupon.model.response.GetCouponReadRes;
import com.example.backend_admin.coupon.model.response.PostCouponCreateRes;
import com.example.backend_admin.coupon.repository.CouponRepository;
import com.example.backend_admin.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final SetCouponTargetService setCouponTargetService;
    private final AdminRepository adminRepository;
    private final CouponTargetUploadService couponTargetUploadService;


    public PostCouponCreateRes create(String token, PostCouponCreateReq postCouponCreateReq) throws BaseException, IOException {
        token = TokenProvider.replaceToken(token);
        Long adminIdx = TokenProvider.getIdx(token);
        Optional<Admin> result = adminRepository.findById(adminIdx);
        if (result.isPresent()) {
            Admin admin = result.get();
            Coupon coupon = couponRepository.save(Coupon.builder()
                    .discount(postCouponCreateReq.getDiscount())
                    .couponCategory(postCouponCreateReq.getCouponCategory())
                    .admin(admin)
                    .build());
            setCouponTargetService.writeFile(postCouponCreateReq, coupon.getIdx());
            System.out.println(couponTargetUploadService.saveFile());
            return PostCouponCreateRes.builder()
                    .couponIdx(coupon.getIdx())
                    .adminIdx(coupon.getAdmin().getIdx())
                    .couponCategory(coupon.getCouponCategory())
                    .discount(coupon.getDiscount())
                    .build();
        }else{
            return null; //존재하지 않는 사용자 예외처리
        }
    }

    public List<GetCouponReadRes> list() throws BaseException {
        List<Coupon> couponList = couponRepository.findAll();

        List<GetCouponReadRes> getCouponReadResList = new ArrayList<>();

        for (Coupon coupon : couponList) {
            getCouponReadResList.add(GetCouponReadRes.builder()
                    .idx(coupon.getIdx())
                    .discount(coupon.getDiscount())
                    .couponCategory(coupon.getCouponCategory())
                    .getAdminReadRes(GetAdminReadRes.builder()
                            .idx(coupon.getAdmin().getIdx())
                            .adminEmail(coupon.getAdmin().getAdminEmail())
                            .build())
                    .build());
        }
        return getCouponReadResList;
    }
    public GetCouponReadRes read(Long idx) throws BaseException {
        Optional<Coupon> result = couponRepository.findById(idx);

        if (result.isPresent()){
            Coupon coupon = result.get();
            return GetCouponReadRes.builder()
                    .idx(coupon.getIdx())
                    .couponCategory(coupon.getCouponCategory())
                    .discount(coupon.getDiscount())
                    .getAdminReadRes(GetAdminReadRes.builder()
                            .idx(coupon.getAdmin().getIdx())
                            .adminEmail(coupon.getAdmin().getAdminEmail())
                            .build())
                    .build();
        }
        return null;
    }
}
