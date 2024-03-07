package com.example.backend.orders.model.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable=false)
    private Long consumerIdx;

    @Column(nullable=false)
    private String impUid;

    private Long productIdx;


    public static Orders dtoToEntity(String impUid, Long consumerIdx, Long productIdx ) {
        return Orders.builder()
                .consumerIdx(consumerIdx)
                .impUid(impUid)
                .productIdx(productIdx)
                .build();
    }

}
