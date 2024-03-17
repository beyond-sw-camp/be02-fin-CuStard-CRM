package com.example.backend.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private final Boolean isSuccess;
    private final Integer code;
    private final String message;
    private T result;

    public static <T> BaseResponse<T> successResponse( T result) {
        return new BaseResponse<>(true, BaseResponseStatus.SUCCESS.getCode(), BaseResponseStatus.SUCCESS.getMessage(), result);
    }

    public static <T> BaseResponse<T> failResponse(BaseResponseStatus baseResponseStatus) {
        return new BaseResponse<>(false, baseResponseStatus.getCode(), baseResponseStatus.getMessage(), null);
    }

//    public BaseResponse(BaseResponseStatus status) {
//        this.isSuccess = status.isSuccess();
//        this.message = status.getMessage();
//        this.code = status.getCode();
//    }


}

