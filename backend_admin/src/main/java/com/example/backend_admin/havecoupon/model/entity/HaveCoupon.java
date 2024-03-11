package com.example.backend_admin.havecoupon.model.entity;

import com.example.backend_admin.coupon.model.entity.Coupon;
import com.example.backend_admin.customer.entity.Customer;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HaveCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;
    Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    Coupon coupon;
}
