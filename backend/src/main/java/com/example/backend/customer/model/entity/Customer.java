package com.example.backend.customer.model.entity;


import com.example.backend.common.BaseTimeEntity;
import com.example.backend.common.CustomerLevel;
import com.example.backend.havecoupon.model.entity.HaveCoupon;

import com.example.backend.log.entity.LoginLog;
import com.example.backend.log.entity.ProductDetailLog;
import com.example.backend.log.entity.SearchLog;
import com.example.backend.orders.model.entity.Orders;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;
    private String customerEmail;
    private String customerPwd;
    private String authority;
    private boolean status;

    private Integer age;
    private String gender;
    private String address;
    private CustomerLevel level;

    private Integer totalAmount;
    private String lastLogin;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<HaveCoupon> haveCouponList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<LoginLog> loginLogs = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SearchLog> searchLogs = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ProductDetailLog> productDetailLogs = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.customerEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
