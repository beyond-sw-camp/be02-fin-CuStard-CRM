package com.example.backend.qna.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostQnaReadReq {
    @NotBlank
    @Size(min = 4,max = 4)
    @Pattern(regexp = "^[0-9]*$",message = "0~9 숫자 4자리를 입력해주세요.")
    private String qnaPwd;
}
