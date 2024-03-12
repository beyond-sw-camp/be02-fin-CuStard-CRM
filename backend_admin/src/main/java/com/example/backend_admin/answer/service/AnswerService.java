package com.example.backend_admin.answer.service;

import com.example.backend_admin.admin.model.entity.Admin;
import com.example.backend_admin.admin.repository.AdminRepository;
import com.example.backend_admin.answer.model.Answer;
import com.example.backend_admin.answer.model.request.PostAnswerRegisterReq;
import com.example.backend_admin.answer.model.response.PostAnswerRegisterRes;
import com.example.backend_admin.answer.repository.AnswerRepository;
import com.example.backend_admin.common.BaseResponse;
import com.example.backend_admin.qna.model.Qna;
import com.example.backend_admin.qna.repository.QnaRepository;
import com.example.backend_admin.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QnaRepository qnaRepository;
    private final AdminRepository adminRepository;

    public BaseResponse<PostAnswerRegisterRes> registerAnswer(String token, Long qnaIdx, PostAnswerRegisterReq postAnswerRegisterReq) {
        Optional<Answer> searchdup = answerRepository.findByQnaIdx(qnaIdx); //이미 작성된 답변이 있을 경우
        if (searchdup.isEmpty()) {
            if (postAnswerRegisterReq.getAnswerContent() != null) {
                PostAnswerRegisterRes postAnswerRegisterRes = null;
                Optional<Qna> result = qnaRepository.findById(qnaIdx);
                if (result.isPresent()) {
                    Qna qna = result.get();

                    token = TokenProvider.replaceToken(token);
                    Long customerIdx = TokenProvider.getIdx(token);
                    Optional<Admin> admin = adminRepository.findById(customerIdx);
                    if (admin.isPresent()) {
                        Admin writer = admin.get();

                        Answer answer = Answer.builder()
                                .answerContent(postAnswerRegisterReq.getAnswerContent())
                                .qna(qna)
                                .admin(writer)
                                .build();
                        answerRepository.save(answer);

                        postAnswerRegisterRes = PostAnswerRegisterRes.builder()
                                .title(qna.getTitle())
                                .qnaContent(qna.getQnaContent())
                                .answerContent(answer.getAnswerContent())
                                .build();
                    } else {
                        return BaseResponse.failResponse(401, "관리자 권한이 없습니다.");
                    }
                } else {
                    return BaseResponse.failResponse(404, "존재하지 않는 게시물 입니다.");
                }
                return BaseResponse.successResponse("1:1 문의 작성 성공", postAnswerRegisterRes);
            } else {
                return BaseResponse.failResponse(400, "내용을 입력하지 않았습니다.");
            }
        } else {
            return BaseResponse.failResponse(400, "이미 작성한 답변이 존재합니다.");
        }
    }
}
