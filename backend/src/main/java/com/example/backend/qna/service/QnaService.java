package com.example.backend.qna.service;

import com.example.backend.answer.model.entity.Answer;
import com.example.backend.answer.repository.AnswerRepository;
import com.example.backend.common.BaseResponse;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.repository.CustomerRepository;
import com.example.backend.qna.model.entity.Qna;
import com.example.backend.qna.model.request.PostQnaReadReq;
import com.example.backend.qna.model.request.PostQnaRegisterReq;
import com.example.backend.qna.model.response.GetQnaListRes;
import com.example.backend.qna.model.response.PostQnaReadRes;
import com.example.backend.qna.model.response.PostQnaRegisterRes;
import com.example.backend.qna.repository.QnaRepository;
import com.example.backend.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;
    private final AnswerRepository answerRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public BaseResponse<PostQnaRegisterRes> registerQna(String token, PostQnaRegisterReq postQnaRegisterReq) {
        token = TokenProvider.replaceToken(token);
        Long customerIdx = TokenProvider.getIdx(token);
        Optional<Customer> result = customerRepository.findById(customerIdx);

        if (result.isPresent()) {
            if (postQnaRegisterReq.getTitle() == null) {
                return BaseResponse.failResponse(400, "제목을 입력하지 않았습니다.");
            } else if (postQnaRegisterReq.getQnaContent() == null) {
                return BaseResponse.failResponse(400, "내용을 입력하지 않았습니다.");
            } else if (postQnaRegisterReq.getQnaPwd() == null) {
                return BaseResponse.failResponse(400, "비밀번호를 입력하지 않았습니다.");
            } else {
                Customer customer = result.get();
                Qna qna = Qna.builder()
                        .title(postQnaRegisterReq.getTitle())
                        .qnaPwd(passwordEncoder.encode(postQnaRegisterReq.getQnaPwd()))
                        .qnaContent(postQnaRegisterReq.getQnaContent())
                        .customer(customer)
                        .build();

                qnaRepository.save(qna);

                PostQnaRegisterRes postQnaRegisterRes = PostQnaRegisterRes.builder()
                        .title(qna.getTitle())
                        .qnaContent(qna.getQnaContent())
                        .build();
                return BaseResponse.successResponse("1:1 문의 작성 성공",postQnaRegisterRes);
            }
        } else {
            return BaseResponse.failResponse(400, "존재하지 않는 사용자입니다.");

        }
    }

    public List<GetQnaListRes> list() {
        List<Qna> resultQna = qnaRepository.findAll();
        List<GetQnaListRes> getQnaListRes = new ArrayList<>();

        for (Qna qna : resultQna) {
            GetQnaListRes qnaListRes = new GetQnaListRes();
            qnaListRes.setIdx(qna.getIdx());
            qnaListRes.setTitle(qna.getTitle());
            Optional<Answer> resultAnswer = answerRepository.findByQnaIdx(qnaListRes.getIdx());
            if (resultAnswer.isPresent()) {
                Answer answer = resultAnswer.get();
                qnaListRes.setAnswerContent(answer.getAnswerContent());
            }
            getQnaListRes.add(qnaListRes);
        }
        return getQnaListRes;
    }

    public PostQnaReadRes readQna(Long idx, PostQnaReadReq postQnaReadReq) {
        Optional<Qna> result = qnaRepository.findById(idx);
        if (result.isPresent()) {
            Qna qna = result.get();
            Boolean passwordCheck = passwordEncoder.matches(postQnaReadReq.getQnaPwd(), qna.getQnaPwd());

            // 비밀번호 일치 여부에 따라 처리
            if (passwordCheck) {
                PostQnaReadRes postQnaReadRes =
                        PostQnaReadRes.builder()
                                .title(qna.getTitle())
                                .qnaContent(qna.getQnaContent())
                                .build();

                return postQnaReadRes;
            } else {
                //비밀번호 일치하지 않음 처리
            }
        } else {
            return null;
            //존재하지 않는 게시글 번호
        }
        return null;
    }
}

