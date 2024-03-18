package com.example.backend_admin.answer.service;

import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.admin.repository.AdminRepository;
import com.example.backend_admin.answer.model.request.PostAnswerRegisterReq;
import com.example.backend_admin.answer.model.response.PostAnswerRegisterRes;
import com.example.backend_admin.answer.repository.AnswerRepository;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.qna.model.Qna;
import com.example.backend_admin.qna.repository.QnaRepository;
import com.example.backend_admin.utils.TokenProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private QnaRepository qnaRepository;
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private TokenProvider tokenProvider;
    @InjectMocks
    private AnswerService answerService;

    private static Admin admin;
    private static Qna qna;
    private static PostAnswerRegisterReq req;

    private static Long qnaIdx;
    private static String token;

    @BeforeAll
    static void setUp() {
        admin = Admin.builder()
                .idx(1L)
                .adminEmail("admin@test.com")
                .adminPwd("qwer1234")
                .authority("Administrator")
                .build();
        qna = Qna.builder()
                .idx(1L)
                .title("test qna title")
                .qnaContent("test qna content")
                .qnaPwd("qwer1234")
                .build();
        req = PostAnswerRegisterReq.builder()
                .answerContent("test qna answer")
                .build();
        qnaIdx = 1L;
        token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluQHRlc3QuY29tIiwicm9sZSI6IkFkbWluaXN0cmF0b3IiLCJpZHgiOjIsImlhdCI6MTcxMDc0NzE3NiwiZXhwIjoxNzExMDQ3MTc2fQ.T1rWol_obXUE_pRBz_bjZ2jaoiYHy59L9kosdFjcnw0";
    }

    @Test
    void answerService_register_success(){
        Authentication authentication = new TestingAuthenticationToken(admin.getAdminEmail(), admin.getAdminPwd(),admin.getAuthority());

        try (MockedStatic<TokenProvider> tokenProviderMockedStatic = mockStatic(TokenProvider.class)) {

            given(answerRepository.findByQnaIdx(qnaIdx)).willReturn(Optional.empty());
            given(qnaRepository.findById(any(Long.class))).willReturn(Optional.of(qna));
            tokenProviderMockedStatic.when(() -> TokenProvider.getIdx(any(String.class))).thenReturn(1L);
            given(adminRepository.findById(any(Long.class))).willReturn(Optional.of(admin));

            BaseResponse<PostAnswerRegisterRes> response = answerService.registerAnswer(token, qnaIdx, req);

            assertEquals("1:1 문의 작성 성공", response.getMessage());
            PostAnswerRegisterRes result = response.getResult();
            assertEquals(qna.getTitle(), result.getTitle());
            assertEquals(qna.getQnaContent(), result.getQnaContent());
            assertEquals(req.getAnswerContent(), result.getAnswerContent());
        }
    }

    @Test
    void answerService_register_fail_exist(){

    }

}
