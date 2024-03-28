package com.example.backend_admin.answer.service;

import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.admin.repository.AdminRepository;
import com.example.backend_admin.answer.model.Answer;
import com.example.backend_admin.answer.model.request.PostAnswerRegisterReq;
import com.example.backend_admin.answer.model.response.PostAnswerRegisterRes;
import com.example.backend_admin.answer.repository.AnswerRepository;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.qna.model.Qna;
import com.example.backend_admin.qna.repository.QnaRepository;
import com.example.backend_admin.utils.TokenProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static com.example.backend_admin.common.BaseResponseStatus.*;
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
    private AnswerService answerService;

    private static Admin admin;
    private static Qna qna1;
    private static Qna qna2;
    private static Answer answer;
    private static PostAnswerRegisterReq req;

    private static Long qnaIdx;
    private static String token;
    @BeforeEach
    void serviceCall(){
        answerService = new AnswerService(answerRepository, qnaRepository, adminRepository);
    }


    @BeforeAll
    static void setUp() {
        admin = Admin.builder()
                .idx(1L)
                .adminEmail("admin@test.com")
                .adminPwd("qwer1234")
                .authority("Administrator")
                .build();
        qna1 = Qna.builder()
                .idx(1L)
                .title("test qna1 title")
                .qnaContent("test qna1 content")
                .qnaPwd("qwer1234")
                .build();
        qna2 = Qna.builder()
                .idx(2L)
                .title("test qna2 title")
                .qnaContent("test qna2 content")
                .qnaPwd("qwer1234")
                .build();
        answer = Answer.builder()
                .idx(1L)
                .answerContent("test qna answer")
                .qna(qna2)
                .admin(admin)
                .build();
        req = PostAnswerRegisterReq.builder()
                .answerContent("test qna answer")
                .build();
        token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluQHRlc3QuY29tIiwicm9sZSI6IkFkbWluaXN0cmF0b3IiLCJpZHgiOjIsImlhdCI6MTcxMDc0NzE3NiwiZXhwIjoxNzExMDQ3MTc2fQ.T1rWol_obXUE_pRBz_bjZ2jaoiYHy59L9kosdFjcnw0";
    }

    @Test
    void answerService_register_success() throws BaseException {
        qnaIdx = 1L;

        Authentication authentication = new TestingAuthenticationToken(admin.getAdminEmail(), admin.getAdminPwd(),admin.getAuthority());

        try (MockedStatic<TokenProvider> tokenProviderMockedStatic = mockStatic(TokenProvider.class)) {

            given(answerRepository.findByQnaIdx(qnaIdx)).willReturn(Optional.empty());
            given(qnaRepository.findById(any(Long.class))).willReturn(Optional.of(qna1));
            tokenProviderMockedStatic.when(() -> TokenProvider.getIdx(any(String.class))).thenReturn(1L);
            given(adminRepository.findById(any(Long.class))).willReturn(Optional.of(admin));

            PostAnswerRegisterRes response = answerService.registerAnswer(token, qnaIdx, req);

            assertEquals(qna1.getTitle(), response.getTitle());
            assertEquals(qna1.getQnaContent(), response.getQnaContent());
            assertEquals(req.getAnswerContent(), response.getAnswerContent());
        }
    }

    @Test
    void answerService_register_fail_exist(){ //답변이 이미 존재할 경우
        qnaIdx = 2L;

        given(answerRepository.findByQnaIdx(qnaIdx)).willReturn(Optional.of(answer));

        BaseException exception = assertThrows(BaseException.class, () -> {
            answerService.registerAnswer(token, qnaIdx, req);
        });

        assertEquals(ADMIN_ANSWER_ISPRESENT, exception.getBaseResponseStatus());
    }

//    @Test
//    void answerService_register_fail_emptyAnswerContent() { //답변 내용이 존재하지 않을 경우
//        qnaIdx = 1L;
//
//        BaseException exception = assertThrows(BaseException.class, () -> {
//            answerService.registerAnswer(token, qnaIdx, PostAnswerRegisterReq.builder().build());
//        });
//
//        assertEquals(ADMIN_ANSWER_EMPTY_ANSWERCONTENT, exception.getBaseResponseStatus());
//    }

    @Test
    void answerService_register_fail_qnaNotFound() { //문의글이 존재하지 않을 경우
        qnaIdx = 3L;

        given(qnaRepository.findById(qnaIdx)).willReturn(Optional.empty());

        BaseException exception = assertThrows(BaseException.class, () -> {
            answerService.registerAnswer(token, qnaIdx, req);
        });

        assertEquals(ADMIN_ANSWER_NOT_FOUND, exception.getBaseResponseStatus());
    }

    @Test
    void answerService_register_fail_emptyToken() { //토큰이 없을 경우
        qnaIdx = 1L;

        Authentication authentication = new TestingAuthenticationToken(admin.getAdminEmail(), admin.getAdminPwd(), admin.getAuthority());

        try (MockedStatic<TokenProvider> tokenProviderMockedStatic = mockStatic(TokenProvider.class)) {

            given(answerRepository.findByQnaIdx(qnaIdx)).willReturn(Optional.empty());
            given(qnaRepository.findById(any(Long.class))).willReturn(Optional.of(qna1));
            tokenProviderMockedStatic.when(() -> TokenProvider.getIdx(any(String.class))).thenReturn(1L);
            given(adminRepository.findById(any(Long.class))).willReturn(Optional.empty());

            BaseException exception = assertThrows(BaseException.class, () -> {
                answerService.registerAnswer("", qnaIdx, req);
            });

            assertEquals(ADMIN_ANSWER_EMPTY_TOKEN, exception.getBaseResponseStatus());
        }
    }

    @Test
    void answerService_register_fail_noAdminFound() { //관리자가 존재하지 않을 경우
        qnaIdx = 1L;

        Authentication authentication = new TestingAuthenticationToken(admin.getAdminEmail(), admin.getAdminPwd(), admin.getAuthority());

        try (MockedStatic<TokenProvider> tokenProviderMockedStatic = mockStatic(TokenProvider.class)) {

            given(answerRepository.findByQnaIdx(qnaIdx)).willReturn(Optional.empty());
            given(qnaRepository.findById(any(Long.class))).willReturn(Optional.of(qna1));
            tokenProviderMockedStatic.when(() -> TokenProvider.getIdx(any(String.class))).thenReturn(1L);
            given(adminRepository.findById(any(Long.class))).willReturn(Optional.empty());

            BaseException exception = assertThrows(BaseException.class, () -> {
                answerService.registerAnswer(token, qnaIdx, req);
            });

            assertEquals(ADMIN_ANSWER_EMPTY_TOKEN, exception.getBaseResponseStatus());
        }
    }

}
