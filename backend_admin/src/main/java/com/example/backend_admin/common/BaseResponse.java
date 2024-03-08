package com.example.backend_admin.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private final Boolean isSuccess;
    private final Integer code;
    private final String message;
    private final T result;

    public static <T> BaseResponse<T> successResponse(String message, T result) {
        return new BaseResponse<>(true, 1000, message, result);
    }

    public static <T> BaseResponse<T> failResponse(Integer code, String message) {
        return new BaseResponse<>(false, code, message, null);
    }
}

