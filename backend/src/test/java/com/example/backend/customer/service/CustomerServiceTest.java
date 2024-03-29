package com.example.backend.customer.service;

import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponseStatus;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.model.request.PostCustomerSignupReq;
import com.example.backend.customer.model.response.PostCustomerSignupRes;
import com.example.backend.customer.repository.CustomerRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.backend.common.BaseResponseStatus.CUSTOMER_SIGNUP_DUPLICATE_EMAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;


    private static Customer customer;
    private static PostCustomerSignupReq postCustomerSignupReq;
    @BeforeAll
    static void setUp() {
        customer = Customer.builder()
                .idx(1L)
                .customerEmail("test01@test.com")
                .customerPwd("qwer1234")
                .build();

        postCustomerSignupReq = PostCustomerSignupReq.builder()
                .customerEmail("test02@test.com")
                .customerPwd("qwer1234")
                .build();
    }
    @Test
    void CustomerService_CreateCustomer_fail_duplicate(){
        //given
        given(customerRepository.findByCustomerEmail(customer.getCustomerEmail())).willReturn(Optional.of(customer));

        //when
        BaseException exception = assertThrows(BaseException.class, () -> {
            customerService.signup(postCustomerSignupReq);
        });

        // then
        assertEquals(CUSTOMER_SIGNUP_DUPLICATE_EMAIL, exception.getBaseResponseStatus());
    }
}