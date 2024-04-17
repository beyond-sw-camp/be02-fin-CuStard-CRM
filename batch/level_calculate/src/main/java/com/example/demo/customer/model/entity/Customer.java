package com.example.demo.customer.model.entity;



import com.example.demo.common.BaseTimeEntity;
import com.example.demo.common.CustomerLevel;
import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String customerEmail;
    private String customerPwd;
    private String authority;
    private boolean status;
    private String name;
    private Integer age;
    private String gender;
    private String address;
    private CustomerLevel level;

    private Integer totalAmount;
    private String lastLogin;
}
