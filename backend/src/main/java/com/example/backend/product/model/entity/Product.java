package com.example.backend.product.model.entity;

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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private Integer category;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productDetails;

    @Column(nullable = false)
    private Integer productPrice;

    @Column(nullable = false)
    private String ProductImage;

}
