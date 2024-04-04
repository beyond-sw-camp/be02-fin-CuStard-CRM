package com.example.backend.coupon.service;

import com.example.backend.coupon.model.entity.Coupon;
import com.example.backend.coupon.model.request.CouponPushNoti;
import com.example.backend.coupon.model.request.PostCouponCreateReq;
import com.example.backend.coupon.model.response.GetCouponListRes;
import com.example.backend.coupon.model.response.GetCouponReadRes;
import com.example.backend.coupon.model.response.PostCouponCreateRes;
import com.example.backend.coupon.repository.CouponRepository;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.repository.CustomerRepository;
import com.example.backend.havecoupon.Repository.HaveCouponRepository;
import com.example.backend.havecoupon.model.entity.HaveCoupon;
import com.example.backend.havecoupon.model.response.GetHaveCouponBaseRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponReadRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final HaveCouponRepository haveCouponRepository;
    private final CustomerRepository customerRepository;

    public List<GetCouponListRes> list() {
        List<Coupon> couponList = couponRepository.findAll();
        List<GetCouponListRes> getCouponListResList = new ArrayList<>();
        for (Coupon coupon : couponList) {
            List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();
            List<HaveCoupon> haveCouponList = coupon.getHaveCouponList();

            for (HaveCoupon haveCoupon : haveCouponList) {
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

    public GetCouponReadRes read(Long idx) {
        Optional<Coupon> result = couponRepository.findById(idx);

        if (result.isPresent()) {
            Coupon coupon = result.get();
            List<GetHaveCouponBaseRes> getHaveCouponBaseResList = new ArrayList<>();

            for (HaveCoupon haveCoupon : coupon.getHaveCouponList()) {
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


    public Object couponPushNoti(CouponPushNoti couponPushNoti) {
        Optional<Customer> customer = customerRepository.findByCustomerEmail(couponPushNoti.getCustomerEmail());

        if (customer.isPresent()) {
            Customer customer1 = customer.get();
            Optional<HaveCoupon> haveCoupon = haveCouponRepository.findFirstByCustomerIdxOrderByCreatedDateDesc(customer.get().getIdx());
            if (haveCoupon.isPresent()) {
//                Optional<Customer> customerIdx = customerRepository.findByCustomerEmail(couponPushNoti.getCustomerEmail());
//                if (customerIdx.isPresent()) {
                    String lastLogin = customer.get().getLastLogin();
                    LocalDateTime createdDate = haveCoupon.get().getCreatedDate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate lastLoginDate = LocalDate.parse(lastLogin, formatter);
                    LocalDate createDateParsed = createdDate.toLocalDate();

                    if (lastLoginDate.isBefore(createDateParsed)) {
                        System.out.println("로그인 데이터가 크리에이트보다 이전이다");

                        return true;
                    }

                    return false;

//                }
            }


        }

        return null;
    }
}
