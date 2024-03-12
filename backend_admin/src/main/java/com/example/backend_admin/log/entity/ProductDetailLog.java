package com.example.backend_admin.log.entity;

import com.example.backend_admin.common.BaseTimeEntity;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.product.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDetailLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "customer_idx")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_idx")
    private Product product;

}

