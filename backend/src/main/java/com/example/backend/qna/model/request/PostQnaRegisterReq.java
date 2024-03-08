package com.example.backend.qna.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostQnaRegisterReq {

    private String title; //제목
    private String qnaContent; //본문
    private String qnaPwd; //비밀번호
}
