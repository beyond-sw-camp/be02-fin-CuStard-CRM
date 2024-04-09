package com.example.backend.qna.model.entity;

import com.example.backend.customer.model.entity.Customer;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String title; //제목

    private String qnaContent; //본문 내용

    private String qnaPwd;

    private String category;

    @OneToOne
    @JoinColumn(name = "customerIdx")
    private Customer customer;

}
