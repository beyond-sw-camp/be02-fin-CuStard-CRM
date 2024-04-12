package com.example.backend_admin.elastic.entity;

import com.example.backend_admin.common.CustomerLevel;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Document(indexName = "customer")
public class CustomerDocument {
    @Id
    private String id;

    @Field(type = FieldType.Long)
    private Long customerIdx;

    @Field(type = FieldType.Integer)
    private int level;
}
