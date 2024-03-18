package com.example.backend_admin.coupon.service;

import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.admin.model.response.GetAdminReadRes;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.coupon.model.entity.Coupon;
import com.example.backend_admin.coupon.model.request.PostCouponCreateReq;
import com.example.backend_admin.coupon.model.response.GetCouponReadRes;
import com.example.backend_admin.coupon.model.response.PostCouponCreateRes;
import com.example.backend_admin.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    public PostCouponCreateRes create(PostCouponCreateReq postCouponCreateReq) throws BaseException {
        Coupon coupon = couponRepository.save(Coupon.builder()
                .admin(Admin.builder().idx(postCouponCreateReq.getAdminIdx()).build())
                .discount(postCouponCreateReq.getDiscount())
                .couponCategory(postCouponCreateReq.getCouponCategory())
                .build());

        return PostCouponCreateRes.builder()
                .couponIdx(coupon.getIdx())
                .adminIdx(coupon.getAdmin().getIdx())
                .couponCategory(coupon.getCouponCategory())
                .discount(coupon.getDiscount())
                .build();
    }
    public List<GetCouponReadRes> list()throws BaseException{
        List<Coupon> couponList = couponRepository.findAll();

        List<GetCouponReadRes> getCouponReadResList = new ArrayList<>();

        for (Coupon coupon:couponList) {
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
    public GetCouponReadRes read(Long idx)throws BaseException{
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
