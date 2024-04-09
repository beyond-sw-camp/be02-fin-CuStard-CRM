package com.example.backend_admin.elastic.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "orders")
public class OrdersDocument {

    @Id
    private String id;

    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd HH:mm:ss")
    private String timestamp;

    @Field(type = FieldType.Integer)
    private int customerIdx;

    @Field(type = FieldType.Integer)
    private int productIdx;

    @Field(type = FieldType.Double)
    private double price;

    @Field(type = FieldType.Text)
    private String impUid;
}