package com.example.backend.qna.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetQnaListRes {
    private Long idx;
    private String title;
    private String answerContent; //답변 내용
    private String category;
    private String qnaContent;
}
