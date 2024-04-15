package com.example.demo.job;

import com.example.demo.coupon.model.entity.Coupon;
import com.example.demo.coupon.repository.CouponRepository;
import com.example.demo.coupon.service.SetCouponTargetService;
import com.example.demo.customer.model.entity.Customer;
import com.example.demo.customer.model.entity.CustomerDocument;
import com.example.demo.customer.repository.CustomerRepository;
import com.example.demo.email.service.EmailService;
import com.example.demo.havecoupon.model.entity.request.HaveCouponCreateReq;
import com.example.demo.havecoupon.service.HaveCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomerItemWriter implements ItemWriter<List<CustomerDocument>> {
    private final SetCouponTargetService setCouponTargetService;
    private final HaveCouponService haveCouponService;
    private final EmailService emailService;
    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;


    @Override
    public void write(List<? extends List<CustomerDocument>> list) throws Exception {
        List<Integer> coupons = setCouponTargetService.extractLevelCoupon();
        List<Coupon> couponList = new ArrayList<>();

        for (Integer coupon : coupons) {
            try {
                Optional<Coupon> result = couponRepository.findById(Long.parseLong(coupon.toString()));
                if (result.isPresent()) {
                    couponList.add(result.get());
                }
            } catch (NumberFormatException e) {
            }
        }
        System.out.println("List : " + list);
        for (int i = 0; i < list.size(); i++) {
            for (CustomerDocument customer : list.get(i)) {
                System.out.println("회원 목록 " + customerRepository.findAll());
                Optional<Customer> result = customerRepository.findById(customer.getIdx());
                if(result.isPresent()) {

                haveCouponService.create(HaveCouponCreateReq.builder()
                        .coupon(couponList.get(i))
                        .customer(result.get())
                        .count(1)
                        .build());
                emailService.sendLevelCoupon(customer.getIdx());
                }else {
                    System.out.println("존재하지 않는 사용자");
                }
            }
        }
    }
}
