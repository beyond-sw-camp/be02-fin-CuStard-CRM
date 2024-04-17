package com.example.demo.customer.model.entity;



import com.example.demo.common.BaseTimeEntity;
import com.example.demo.common.CustomerLevel;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;
    private String customerEmail;
    private String customerPwd;
    private String authority;
    private boolean status;
    private CustomerLevel level;

    private Integer age;
    private String gender;
    private String address;


    private Integer totalAmount;
    private String lastLogin;




}
