package com.example.backend_admin.elastic.model.response;

import com.example.backend_admin.customer.entity.response.GetCustomerReadRes;
import com.example.backend_admin.elastic.model.dto.GetCategoryOrdersRes;
import com.example.backend_admin.elastic.model.dto.GetLoginTimeRes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDetailRes {
    GetLoginTimeRes getLoginTimeRes;
    GetCategoryOrdersRes getCategoryOrdersRes;
    GetCustomerReadRes getCustomerReadRes;
}
