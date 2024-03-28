package com.example.backend_admin.answer.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostAnswerRegisterReq{
    @NotBlank
    private String answerContent;

}

