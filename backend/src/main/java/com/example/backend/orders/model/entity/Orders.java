package com.example.backend.orders.model.entity;

import com.example.backend.common.BaseTimeEntity;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable=false)
    private Long consumerIdx;

    @Column(nullable=false)
    private String impUid;

    private Long productIdx;

    private Integer productPrice;

    public static Orders dtoToEntity(String impUid, Long consumerIdx, Long productIdx,  Integer productPrice ) {
        return Orders.builder()
                .consumerIdx(consumerIdx)
                .impUid(impUid)
                .productIdx(productIdx)
                .productPrice(productPrice)
                .build();
    }

}
