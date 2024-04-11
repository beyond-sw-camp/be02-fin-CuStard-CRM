package com.example.backend.qna.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostQnaRegisterReq {
    @NotBlank
    private String title; //제목
    @NotBlank
    private String qnaContent; //본문
    @NotBlank
    @Size(min = 4,max = 4)
    @Pattern(regexp = "^[0-9]*$",message = "0~9 숫자 4자리를 입력해주세요.")
    private String qnaPwd; //비밀번호
    @NotBlank
    private String category;
}
