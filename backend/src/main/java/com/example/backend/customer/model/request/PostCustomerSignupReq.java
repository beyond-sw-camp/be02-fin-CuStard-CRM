package com.example.backend.customer.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCustomerSignupReq {

    @Size(min = 6, max = 25, message = "최소 6자 이상, 25자 이하의 숫자를 입력하세요")
    @Pattern(regexp = "[A-z0-9]+@[A-z]+\\.[a-z]{2,3}", message = "이메일 형식을 준수해 주세요.")
    private String customerEmail;

    @Size(min = 8, max = 20, message = "최소 8자 이상, 20자 이하의 숫자를 입력하세요")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "알파벳 대소문자(a~z, A~Z), 숫자(0~9)만 입력 가능합니다.")
    private String customerPwd;
    private Integer age;
    private String address;
    private String gender;
    private String name;

}
