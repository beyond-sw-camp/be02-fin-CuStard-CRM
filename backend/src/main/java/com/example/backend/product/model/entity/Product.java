package com.example.backend.product.model.entity;

import com.example.backend.log.entity.ProductDetailLog;
import com.example.backend.log.entity.SearchLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetailLog> productDetailLogs = new ArrayList<>();

}
