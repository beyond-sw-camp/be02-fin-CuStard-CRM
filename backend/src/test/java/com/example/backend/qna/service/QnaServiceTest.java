package com.example.backend.qna.service;

import com.example.backend.answer.repository.AnswerRepository;
import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import com.example.backend.common.CustomerLevel;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.repository.CustomerRepository;
import com.example.backend.qna.model.request.PostQnaRegisterReq;
import com.example.backend.qna.model.response.PostQnaRegisterRes;
import com.example.backend.qna.repository.QnaRepository;
import com.example.backend.utils.TokenProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.example.backend.common.BaseResponseStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class QnaServiceTest {

    @Mock
    private QnaRepository qnaRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private QnaService qnaService;

    private static Customer customer;
    private static String token;
    private static String title;
    private static String qnaContent;
    private static String qnaPwd;

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
        token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRlc3RAdGVzdC5jb20iLCJyb2xlIjoiQ1VTVE9NRVIiLCJpZHgiOjIxLCJpYXQiOjE3MTE2ODEzNTMsImV4cCI6MTcxMTk4MTM1M30.6jWKqwCNQiqJkIZ_NnHGxSx1isAiEKUScTOPLWaD9gI";

        title = "qna test title";
        qnaContent = "qna test Content";
        qnaPwd = "1234";
    }
    @Test
    void qnaService_register_success() throws BaseException {
        Authentication authentication = new TestingAuthenticationToken(customer.getCustomerEmail(), customer.getCustomerPwd(), customer.getAuthority());
        try (MockedStatic<TokenProvider> tokenProviderMockedStatic = mockStatic(TokenProvider.class)) {
            tokenProviderMockedStatic.when(() -> TokenProvider.getIdx(any(String.class))).thenReturn(1L);
            given(customerRepository.findById(any(Long.class))).willReturn(Optional.of(customer));

            BaseResponse response = qnaService.registerQna(token, PostQnaRegisterReq.builder()
                    .title(title)
                    .qnaContent(qnaContent)
                    .qnaPwd(qnaPwd).build());

            assertEquals(true,response.getIsSuccess());
            assertEquals(1000,response.getCode());
            assertEquals("요청에 성공하였습니다.",response.getMessage());
            PostQnaRegisterRes result = (PostQnaRegisterRes) response.getResult();
            assertEquals(title, result.getTitle());
            assertEquals(qnaContent, result.getQnaContent());
        }
    }
    @Test
    void qnaService_register_unauthorized() throws BaseException {
        Authentication authentication = new TestingAuthenticationToken(customer.getCustomerEmail(), customer.getCustomerPwd(), customer.getAuthority());
        try (MockedStatic<TokenProvider> tokenProviderMockedStatic = mockStatic(TokenProvider.class)) {
            tokenProviderMockedStatic.when(() -> TokenProvider.getIdx(any(String.class))).thenReturn(1L);
            given(customerRepository.findById(any(Long.class))).willReturn(Optional.empty());

            BaseException exception = assertThrows(BaseException.class, () -> {
                qnaService.registerQna(token, PostQnaRegisterReq.builder()
                        .title(title)
                        .qnaContent(qnaContent)
                        .qnaPwd(qnaPwd).build());
            });

            assertEquals(QNA_REGISTER_UNAUTHORIZED, exception.getBaseResponseStatus());
        }
    }
    @Test
    void qnaService_register_emptyTitle() throws BaseException {
        Authentication authentication = new TestingAuthenticationToken(customer.getCustomerEmail(), customer.getCustomerPwd(), customer.getAuthority());
        try (MockedStatic<TokenProvider> tokenProviderMockedStatic = mockStatic(TokenProvider.class)) {
            tokenProviderMockedStatic.when(() -> TokenProvider.getIdx(any(String.class))).thenReturn(1L);
            given(customerRepository.findById(any(Long.class))).willReturn(Optional.of(customer));

            BaseException exception = assertThrows(BaseException.class, () -> {
                qnaService.registerQna(token, PostQnaRegisterReq.builder()
                        .qnaContent(qnaContent)
                        .qnaPwd(qnaPwd).build());
            });

            assertEquals(QNA_REGISTER_EMPTY_TITLE, exception.getBaseResponseStatus());

        }
    }
    @Test
    void qnaService_register_emptyContent() throws BaseException {
        Authentication authentication = new TestingAuthenticationToken(customer.getCustomerEmail(), customer.getCustomerPwd(), customer.getAuthority());
        try (MockedStatic<TokenProvider> tokenProviderMockedStatic = mockStatic(TokenProvider.class)) {
            tokenProviderMockedStatic.when(() -> TokenProvider.getIdx(any(String.class))).thenReturn(1L);
            given(customerRepository.findById(any(Long.class))).willReturn(Optional.of(customer));

            BaseException exception = assertThrows(BaseException.class, () -> {
                qnaService.registerQna(token, PostQnaRegisterReq.builder()
                        .title(title)
                        .qnaPwd(qnaPwd).build());
            });

            assertEquals(QNA_REGISTER_EMPTY_QNACONTENT, exception.getBaseResponseStatus());

        }
    }
    @Test
    void qnaService_register_emptyPwd() throws BaseException {
        Authentication authentication = new TestingAuthenticationToken(customer.getCustomerEmail(), customer.getCustomerPwd(), customer.getAuthority());
        try (MockedStatic<TokenProvider> tokenProviderMockedStatic = mockStatic(TokenProvider.class)) {
            tokenProviderMockedStatic.when(() -> TokenProvider.getIdx(any(String.class))).thenReturn(1L);
            given(customerRepository.findById(any(Long.class))).willReturn(Optional.of(customer));

            BaseException exception = assertThrows(BaseException.class, () -> {
                qnaService.registerQna(token, PostQnaRegisterReq.builder()
                        .title(title)
                        .qnaContent(qnaContent).build());
            });

            assertEquals(QNA_REGISTER_EMPTY_PASSWORD, exception.getBaseResponseStatus());

        }
    }
}