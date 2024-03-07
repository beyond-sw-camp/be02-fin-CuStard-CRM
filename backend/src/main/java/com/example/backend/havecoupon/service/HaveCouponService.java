package com.example.backend.havecoupon.service;

import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.model.response.GetCustomerReadRes;
import com.example.backend.havecoupon.Repository.HaveCouponRepository;
import com.example.backend.havecoupon.model.entity.HaveCoupon;
import com.example.backend.havecoupon.model.request.PostHaveCouponCreateReq;
import com.example.backend.havecoupon.model.response.GetHaveCouponListRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponReadRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HaveCouponService {
    private final HaveCouponRepository haveCouponRepository;
    public HaveCoupon create(PostHaveCouponCreateReq postHaveCouponCreateReq){
        HaveCoupon haveCoupon = haveCouponRepository.save(HaveCoupon.builder()
                .count(postHaveCouponCreateReq.getCount())
                        .customer(Customer.builder()
                                .idx(postHaveCouponCreateReq.getCustomerIdx())
                                .build())
                .build());

        return HaveCoupon.builder()
                .idx(haveCoupon.getIdx())
                .count(haveCoupon.getCount())
                .customer(Customer.builder()
                        .idx(haveCoupon.getCustomer().getIdx())
                        .build())
                .build();
    }
    public List<GetHaveCouponListRes> list(){
        List<HaveCoupon> result = haveCouponRepository.findAll();
        List<GetHaveCouponListRes> getHaveCouponListResList = new ArrayList<>();

        for (HaveCoupon haveCoupon: result) {

            Customer customer = haveCoupon.getCustomer();

            getHaveCouponListResList.add(GetHaveCouponListRes.builder()
                    .idx(haveCoupon.getIdx())
                    .count(haveCoupon.getCount())
                    .getCustomerReadRes(GetCustomerReadRes.builder()
                            .customerEmail(customer.getCustomerEmail())
                            .authority(customer.getAuthority())
                            .build())
                    .build());
        }

        return getHaveCouponListResList;

    }
    public GetHaveCouponReadRes read(Long idx){
        Optional<HaveCoupon> result = haveCouponRepository.findById(idx);

        if(result.isPresent()){
            HaveCoupon haveCoupon = result.get();
            Customer customer = haveCoupon.getCustomer();
            return GetHaveCouponReadRes.builder()
                    .idx(haveCoupon.getIdx())
                    .count(haveCoupon.getCount())
                    .getCustomerReadRes(GetCustomerReadRes.builder()
                            .customerEmail(customer.getCustomerEmail())
                            .authority(customer.getAuthority())
                            .build())
                    .build();
        }
        return null;
    }
    public void delete(){
    }
}
