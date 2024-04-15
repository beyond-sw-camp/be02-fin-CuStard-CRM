package com.example.demo.job;

import com.example.demo.coupon.service.SetCouponTargetService;
import com.example.demo.customer.model.entity.CustomerDocument;
import com.example.demo.email.service.EmailService;
import com.example.demo.havecoupon.model.entity.request.HaveCouponCreateReq;
import com.example.demo.havecoupon.service.HaveCouponService;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

public class CustomerItemWriter implements ItemWriter<List<CustomerDocument>> {
    private SetCouponTargetService setCouponTargetService;
    private HaveCouponService haveCouponService;
    private EmailService emailService;

    public CustomerItemWriter(SetCouponTargetService setCouponTargetService, HaveCouponService haveCouponService, EmailService emailService) {
        this.setCouponTargetService = setCouponTargetService;
        this.haveCouponService = haveCouponService;
        this.emailService = emailService;
    }

    @Override
    public void write(List<? extends List<CustomerDocument>> list) throws Exception {
        List<Integer> coupons = setCouponTargetService.extractLevelCoupon();
        List<Long> couponIdxs = new ArrayList<>();

        for (Integer coupon : coupons) {
            try {
                couponIdxs.add(Long.parseLong(coupon.toString()));
            } catch (NumberFormatException e) {
            }
        }
        System.out.println("List : " + list);
        for (int i = 0; i < list.size(); i++) {
            for (CustomerDocument customer : list.get(i)) {

                haveCouponService.create(HaveCouponCreateReq.builder()
                        .couponIdx(couponIdxs.get(i))
                        .customerIdx(customer.getIdx())
                        .count(1)
                        .build());
                emailService.sendLevelCoupon(customer.getIdx());
            }
        }
    }
}
