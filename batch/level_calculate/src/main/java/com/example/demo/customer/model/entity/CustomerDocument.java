package com.example.demo.customer.model.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Document(indexName = "customer")
public class CustomerDocument {
    @Id
    private Long idx;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "customer_email")
    private String customerEmail;

    @Field(type = FieldType.Text, name = "customer_pwd")
    private String customerPwd;

    @Field(type = FieldType.Text, name = "authority")
    private String authority;

    @Field(type = FieldType.Boolean, name = "status")
    private boolean status;

    @Field(type = FieldType.Integer, name = "age")
    private Integer age;

    @Field(type = FieldType.Text, name = "gender")
    private String gender;

    @Field(type = FieldType.Text, name = "address")
    private String address;

    @Field(type = FieldType.Integer, name = "level")
    private Integer level;

    @Field(type = FieldType.Integer, name = "total_amount")
    private Integer totalAmount;

    @Field(type = FieldType.Date, name = "last_login")
    private String lastLogin;

}
