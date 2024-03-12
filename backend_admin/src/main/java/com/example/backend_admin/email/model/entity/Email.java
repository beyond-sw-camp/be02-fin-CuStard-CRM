package com.example.backend_admin.email.model.entity;

import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.customer.entity.Customer;
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

    @OneToOne
    @JoinColumn(name = "adminIdx")
    private Admin admin;
}
