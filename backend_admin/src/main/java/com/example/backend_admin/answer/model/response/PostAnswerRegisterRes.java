package com.example.backend_admin.answer.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostAnswerRegisterRes {

    private String title; //답변한 글의 제목
    private String qnaContent;//답변한 글의 내용
    private String answerContent; //답변한 내용


}

