package com.example.backend.log.entity;

import com.example.backend.common.BaseTimeEntity;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.product.model.entity.Product;
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
public class SearchLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String keyword;

    @ManyToOne
    @JoinColumn(name = "customer_idx", nullable = false)
    private Customer customer;

}
