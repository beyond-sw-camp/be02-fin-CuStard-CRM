package com.example.backend_admin.qna.service;

import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.answer.model.Answer;
import com.example.backend_admin.answer.repository.AnswerRepository;
import com.example.backend_admin.common.BaseException;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.common.CustomerLevel;
import com.example.backend_admin.customer.entity.Customer;
import com.example.backend_admin.qna.model.Qna;
import com.example.backend_admin.qna.model.response.GetQnaListRes;
import com.example.backend_admin.qna.model.response.PostQnaReadRes;
import com.example.backend_admin.qna.repository.QnaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.backend_admin.common.BaseResponseStatus.QNA_REGISTER_NOT_EXIST_QNA;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class QnaServiceTest {

    @Mock
    private QnaRepository qnaRepository;
    @Mock
    private AnswerRepository answerRepository;
    @InjectMocks
    private QnaService qnaService;

    private static Customer customer;
    private static Admin admin;
    private static Qna qna1;
    private static Qna qna2;
    private static Answer answer;

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
        admin = Admin.builder()
                .idx(1L)
                .adminEmail("admin@test.com")
                .adminPwd("qwer1234")
                .authority("Administrator")
                .build();
        qna1 = Qna.builder()
                .idx(1L)
                .title("test qna title")
                .qnaContent("test qna content")
                .qnaPwd("qwer1234")
                .customer(customer)
                .build();
        qna2 = Qna.builder()
                .idx(2L)
                .title("test qna title")
                .qnaContent("test qna content")
                .qnaPwd("qwer1234")
                .customer(customer)
                .build();
        answer = Answer.builder()
                .idx(1L)
                .answerContent("test qna answer")
                .qna(qna2)
                .admin(admin)
                .build();
    }

    @Test
    void qnaService_list_success() throws BaseException {
        //given
        List<Qna> result = new ArrayList<>();
        result.add(qna1);
        given(qnaRepository.findAll()).willReturn(result);

        //when
        List<GetQnaListRes> response = qnaService.list();

        //then
        assertNotNull(response);
        assertEquals(1, response.size());
        GetQnaListRes qnaListRes = response.get(0);
        assertEquals(qna1.getIdx(), qnaListRes.getIdx());
        assertEquals(qna1.getTitle(), qnaListRes.getTitle());
        assertEquals(qna1.getCustomer().getIdx(), qnaListRes.getCustomerIdx());
    }

    @Test
    void qnaService_readQnaWithoutAnswer_success() throws BaseException{
        // given
        given(qnaRepository.findById(any(Long.class))).willReturn(Optional.of(qna1));

        //when
        PostQnaReadRes response = qnaService.readQna(1L);

        //then
        assertEquals(qna1.getTitle(), response.getTitle());
        assertEquals(qna1.getQnaContent(), response.getQnaContent());
        assertNull(response.getAnswerContent());
    }

    @Test
    void qnaService_readQnaWithAnswer_success() throws BaseException{
        // given
        given(qnaRepository.findById(any(Long.class))).willReturn(Optional.of(qna2));
        given(answerRepository.findByQnaIdx(any(Long.class))).willReturn(Optional.of(answer));

        //when
        PostQnaReadRes response = qnaService.readQna(2L);

        //then
        assertNotNull(response);
        assertEquals(qna2.getTitle(), response.getTitle());
        assertEquals(qna2.getQnaContent(), response.getQnaContent());
        assertEquals(answer.getAnswerContent(), response.getAnswerContent());
    }

    @Test
    void qnaService_readQna_fail_null(){
        //given
        given(qnaRepository.findById(any(Long.class))).willReturn(Optional.empty());

        // when
        BaseException exception = assertThrows(BaseException.class, () -> {
            qnaService.readQna(1L);
        });

        // then
        assertEquals(QNA_REGISTER_NOT_EXIST_QNA, exception.getBaseResponseStatus());

    }
}