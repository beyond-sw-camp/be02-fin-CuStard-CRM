package com.example.backend.havecoupon.service;

import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponseStatus;
import com.example.backend.coupon.model.entity.Coupon;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.havecoupon.Repository.HaveCouponRepository;
import com.example.backend.havecoupon.model.entity.HaveCoupon;
import com.example.backend.havecoupon.model.request.PostHaveCouponCreateReq;
import com.example.backend.havecoupon.model.response.GetHaveCouponListRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponReadRes;
import com.example.backend.havecoupon.model.response.PostHaveCouponCreateRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HaveCouponService {
    private final HaveCouponRepository haveCouponRepository;


    public List<GetHaveCouponListRes> list()throws BaseException {
        List<HaveCoupon> result = haveCouponRepository.findAll();
        List<GetHaveCouponListRes> getHaveCouponListResList = new ArrayList<>();

        if (result.isEmpty()){
            throw new BaseException(BaseResponseStatus.HAVECOUPON_LIST_EMPTY);
        }
        for (HaveCoupon haveCoupon: result) {

            Customer customer = haveCoupon.getCustomer();
            Coupon coupon = haveCoupon.getCoupon();
            getHaveCouponListResList.add(GetHaveCouponListRes.builder()
                    .idx(haveCoupon.getIdx())
                    .count(haveCoupon.getCount())
                    .customerIdx(customer.getIdx())
                    .couponIdx(coupon.getIdx())
                    .build());
        }

        return getHaveCouponListResList;

    }
    public GetHaveCouponReadRes read(Long idx) throws BaseException{
        Optional<HaveCoupon> result = haveCouponRepository.findById(idx);

        if(result.isPresent()){
            HaveCoupon haveCoupon = result.get();
            Customer customer = haveCoupon.getCustomer();
            Coupon coupon = haveCoupon.getCoupon();
            return GetHaveCouponReadRes.builder()
                    .idx(haveCoupon.getIdx())
                    .count(haveCoupon.getCount())
                    .customerIdx(customer.getIdx())
                    .couponIdx(coupon.getIdx())
                    .discount(coupon.getDiscount())
                    .build();
        }
        throw new BaseException(BaseResponseStatus.HAVECOUPON_LIST_EMPTY);
    }

}
