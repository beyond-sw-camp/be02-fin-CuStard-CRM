package com.example.demo.login.model.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Document(indexName = "login")
public class LoginDocument {

    @Id
    private String id;

    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    @Field(type = FieldType.Integer)
    private int customerIdx;

    @Field(type = FieldType.Keyword)
    private String customerEmail;

    @Override
    public String toString() {
        return "LoginDocument{" +
                "timestamp=" + timestamp +
                ", customerIdx='" + customerIdx + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }
}