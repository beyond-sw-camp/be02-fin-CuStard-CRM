package com.example.backend.coupon.model.entity;

import com.example.backend.admin.model.entity.Admin;
import com.example.backend.havecoupon.model.entity.HaveCoupon;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private Integer discount;
    private Integer couponCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coupon")
    private List<HaveCoupon> haveCouponList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
