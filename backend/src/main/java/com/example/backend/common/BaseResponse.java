package com.example.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private final Boolean isSuccess;
    private final Integer code;
    private final String message;
    private T result;

    public static <T> BaseResponse<T> successResponse(T result) {
        return new BaseResponse<>(true, BaseResponseStatus.SUCCESS.getCode(), BaseResponseStatus.SUCCESS.getMessage(), result);
    }

    public static <T> BaseResponse<T> failResponse(BaseResponseStatus baseResponseStatus) {
        return new BaseResponse<>(false, baseResponseStatus.getCode(), baseResponseStatus.getMessage(), null);
    }
}

