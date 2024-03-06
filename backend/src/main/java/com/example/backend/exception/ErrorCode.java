package com.example.backend.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 공통 에러 코드
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "입력값이 잘못되었습니다.."),
    
    // 사용자 관련 에러 코드
    DUPLICATED_USER(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),

    PROFILE_IMAGE_EMPTY(HttpStatus.UNAUTHORIZED, "사진을 첨부해주세요."),


    // TODO: 코스, 섹션, 강의 추가시 발생할 수 있는 에러 코드 추가할 것
    // 코스 관련 에러 코드
    DUPLICATED_COURSE(HttpStatus.CONFLICT, "중복된 코스 이름이 존재합니다."),

    ;

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private final HttpStatus status;
    private final String message;
}
