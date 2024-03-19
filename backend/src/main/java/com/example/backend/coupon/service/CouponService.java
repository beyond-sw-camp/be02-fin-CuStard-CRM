package com.example.backend.coupon.service;

import com.example.backend.coupon.model.entity.Coupon;
import com.example.backend.coupon.model.request.PostCouponCreateReq;
import com.example.backend.coupon.model.response.GetCouponListRes;
import com.example.backend.coupon.model.response.GetCouponReadRes;
import com.example.backend.coupon.model.response.PostCouponCreateRes;
import com.example.backend.coupon.repository.CouponRepository;
import com.example.backend.havecoupon.model.entity.HaveCoupon;
import com.example.backend.havecoupon.model.response.GetHaveCouponBaseRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponReadRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;


    public List<GetCouponListRes> list(){
        List<Coupon> couponList = couponRepository.findAll();
        List<GetCouponListRes> getCouponListResList = new ArrayList<>();
        for (Coupon coupon:couponList) {
            List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();
            List<HaveCoupon> haveCouponList = coupon.getHaveCouponList();

            for (HaveCoupon haveCoupon:haveCouponList) {
                getHaveCouponBaseResList.add(GetHaveCouponBaseRes.builder()
                        .idx(haveCoupon.getIdx())
                        .count(haveCoupon.getCount())
                        .build());
            }
            GetCouponListRes getCouponListRes = GetCouponListRes.builder()
                    .idx(coupon.getIdx())
                    .discount(coupon.getDiscount())
                    .couponCategory(coupon.getCouponCategory())
                    .getHaveCouponBaseResList(getHaveCouponBaseResList)
                    .build();

            getCouponListResList.add(getCouponListRes);
        }
        return getCouponListResList;
    }
    public GetCouponReadRes read(Long idx){
        Optional<Coupon> result = couponRepository.findById(idx);

        if(result.isPresent()){
            Coupon coupon = result.get();
            List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();

            for (HaveCoupon haveCoupon: coupon.getHaveCouponList()) {
                getHaveCouponBaseResList.add(GetHaveCouponBaseRes.builder()
                        .idx(haveCoupon.getIdx())
                        .count(haveCoupon.getCount())
                        .build());
            }

            return GetCouponReadRes.builder()
                    .idx(coupon.getIdx())
                    .discount(coupon.getDiscount())
                    .couponCategory(coupon.getCouponCategory())
                    .getHaveCouponBaseResList(getHaveCouponBaseResList)
                    .build();
        }
        return null;
    }


}
