package com.example.demo.job;

import com.example.demo.customer.model.entity.Customer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerBatchRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public CustomerBatchRepository(JPAQueryFactory queryFactory) {
        super(Customer.class);
        this.jpaQueryFactory = queryFactory;
    }
}


