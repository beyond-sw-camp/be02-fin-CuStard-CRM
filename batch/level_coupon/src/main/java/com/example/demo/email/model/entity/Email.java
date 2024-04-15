package com.example.demo.email.model.entity;


import com.example.demo.customer.model.entity.Customer;
import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 15000)
    private String emailContent;

    @OneToOne
    @JoinColumn(name = "customerIdx")
    private Customer customer;
}
