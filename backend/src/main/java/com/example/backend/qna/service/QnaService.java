package com.example.backend.qna.service;

import com.example.backend.answer.model.entity.Answer;
import com.example.backend.answer.repository.AnswerRepository;
import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import com.example.backend.common.BaseResponseStatus;
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

import static com.example.backend.common.BaseResponseStatus.QNA_REGISTER_INCORRECT_PASSWORD;
import static com.example.backend.common.BaseResponseStatus.QNA_REGISTER_NOT_EXIST_QNA;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;
    private final AnswerRepository answerRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

        public BaseResponse registerQna(String token, PostQnaRegisterReq postQnaRegisterReq) throws BaseException {
            token = TokenProvider.replaceToken(token);
            Long customerIdx = TokenProvider.getIdx(token);
            Optional<Customer> result = customerRepository.findById(customerIdx);

            if (result.isPresent()) {
                if (postQnaRegisterReq.getTitle() == null) {
                    throw new BaseException (BaseResponseStatus.QNA_REGISTER_EMPTY_TITLE);
                } else if (postQnaRegisterReq.getQnaContent() == null) {
                    throw new BaseException (BaseResponseStatus.QNA_REGISTER_EMPTY_QNACONTENT);
                } else if (postQnaRegisterReq.getQnaPwd() == null) {
                    throw new BaseException (BaseResponseStatus.QNA_REGISTER_EMPTY_PASSWORD);
                } else {
                    Customer customer = result.get();
                    Qna qna = Qna.builder()
                            .title(postQnaRegisterReq.getTitle())
                            .qnaPwd(passwordEncoder.encode(postQnaRegisterReq.getQnaPwd()))
                            .qnaContent(postQnaRegisterReq.getQnaContent())
                            .customer(customer)
                            .category(postQnaRegisterReq.getCategory())
                            .build();

                    qnaRepository.save(qna);

                    PostQnaRegisterRes postQnaRegisterRes = PostQnaRegisterRes.builder()
                            .title(qna.getTitle())
                            .qnaContent(qna.getQnaContent())
                            .build();
                    return BaseResponse.successResponse(postQnaRegisterRes);
                }
            } else {
                throw new BaseException(BaseResponseStatus.QNA_REGISTER_UNAUTHORIZED);

            }
        }

    public List<GetQnaListRes> list() throws BaseException{
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

    public PostQnaReadRes readQna(Long idx, PostQnaReadReq postQnaReadReq) throws BaseException{
        Optional<Qna> result = qnaRepository.findById(idx);
        if (result.isPresent()) {
            Qna qna = result.get();
            Boolean passwordCheck = passwordEncoder.matches(postQnaReadReq.getQnaPwd(), qna.getQnaPwd());

            // 비밀번호 일치 여부에 따라 처리
            if (passwordCheck) {
                Optional<Answer> resultAnswer = answerRepository.findByQnaIdx(idx);
                if (resultAnswer.isPresent()) {
                    Answer answer = resultAnswer.get();
                    return PostQnaReadRes.builder()
                            .title(qna.getTitle())
                            .qnaContent(qna.getQnaContent())
                            .answerContent(answer.getAnswerContent())
                            .build();
                    //답변이 있는 경우
                } else {
                    return PostQnaReadRes.builder()
                            .title(qna.getTitle())
                            .qnaContent(qna.getQnaContent())
                            .build();
                    //답변이 없는 경우
                }
            } else {
                //비밀번호 일치하지 않음 처리
                throw new BaseException(QNA_REGISTER_INCORRECT_PASSWORD);
            }
        }
        throw new BaseException(QNA_REGISTER_NOT_EXIST_QNA);


    }
}

