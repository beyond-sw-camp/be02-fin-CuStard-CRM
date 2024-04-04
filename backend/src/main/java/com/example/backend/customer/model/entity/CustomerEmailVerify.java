package com.example.backend.customer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class CustomerEmailVerify {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    private String customerEmail;
    private String uuid;

}
