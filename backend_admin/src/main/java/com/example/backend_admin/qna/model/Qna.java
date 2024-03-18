package com.example.backend_admin.qna.model;

import lombok.*;
import javax.persistence.*;
import com.example.backend_admin.customer.entity.Customer;

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

    @OneToOne
    @JoinColumn(name = "customerIdx")
    private Customer customer;

}
