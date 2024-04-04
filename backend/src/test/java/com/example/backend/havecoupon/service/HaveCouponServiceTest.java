package com.example.backend.havecoupon.service;

import com.example.backend.common.BaseException;
import com.example.backend.common.CustomerLevel;
import com.example.backend.coupon.model.entity.Coupon;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.havecoupon.Repository.HaveCouponRepository;
import com.example.backend.havecoupon.model.entity.HaveCoupon;
import com.example.backend.havecoupon.model.response.GetHaveCouponBaseRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponListRes;
import com.example.backend.havecoupon.model.response.GetHaveCouponReadRes;
import com.example.backend.qna.model.request.PostQnaRegisterReq;
import com.example.backend.qna.repository.QnaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.backend.common.BaseResponseStatus.HAVECOUPON_LIST_EMPTY;
import static com.example.backend.common.BaseResponseStatus.QNA_REGISTER_EMPTY_TITLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class HaveCouponServiceTest {
    @Mock
    private HaveCouponRepository haveCouponRepository;
    @InjectMocks
    private HaveCouponService haveCouponService;
    private static Customer customer;
    private static Coupon coupon;
    private static HaveCoupon haveCoupon;

    @BeforeAll
    static void setUp() {
        customer = Customer.builder()
                .idx(1L)
                .customerEmail("test@test.com")
                .customerPwd("qwer1234")
                .authority("CUSTOMER")
                .status(true)
                .level(CustomerLevel.NEWBIE)
                .build();
        coupon = Coupon.builder()
                .idx(1L)
                .discount(10)
                .couponCategory(1)
                .build();
        haveCoupon = HaveCoupon.builder()
                .idx(1L)
                .count(1)
                .customer(customer)
                .coupon(coupon)
                .build();
    }

    @Test
    void haveCouponService_list_success() throws BaseException {
        List<HaveCoupon> result = new ArrayList<>();
        result.add(haveCoupon);
        given(haveCouponRepository.findAll()).willReturn(result);

        List<GetHaveCouponListRes> response = haveCouponService.list();

        assertNotNull(response);
        assertEquals(1, response.size());
        GetHaveCouponListRes getHaveCouponListRes = response.get(0);
        assertEquals(1L, getHaveCouponListRes.getIdx());
        assertEquals(1, getHaveCouponListRes.getCount());
        assertEquals(customer.getIdx(), getHaveCouponListRes.getCustomerIdx());
        assertEquals(coupon.getIdx(), getHaveCouponListRes.getCouponIdx());
    }

    @Test
    void haveCouponService_list_emptyList() throws BaseException {
        given(haveCouponRepository.findAll()).willReturn(new ArrayList<>());
        BaseException exception = assertThrows(BaseException.class, () -> {
            List<GetHaveCouponListRes> response = haveCouponService.list();
        });
        assertEquals(HAVECOUPON_LIST_EMPTY, exception.getBaseResponseStatus());
    }

    @Test
    void haveCouponService_read_success() throws BaseException {
        given(haveCouponRepository.findById(any(Long.class))).willReturn(Optional.of(haveCoupon));

        GetHaveCouponReadRes response = haveCouponService.read(1L);

        assertEquals(haveCoupon.getIdx(),response.getIdx());
        assertEquals(haveCoupon.getCount(),response.getCount());
        assertEquals(haveCoupon.getCustomer().getIdx(),response.getCustomerIdx());
        assertEquals(haveCoupon.getCoupon().getIdx(),response.getCouponIdx());
    }

    @Test
    void haveCouponService_read_emptyList() throws BaseException {
        given(haveCouponRepository.findAll()).willReturn(new ArrayList<>());
        BaseException exception = assertThrows(BaseException.class, () -> {
            List<GetHaveCouponListRes> response = haveCouponService.list();
        });
        assertEquals(HAVECOUPON_LIST_EMPTY, exception.getBaseResponseStatus());
    }

}