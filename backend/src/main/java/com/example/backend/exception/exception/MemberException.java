package com.example.backend.exception.exception;



import com.example.backend.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;


    public MemberException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return String.format("[%s] %s", message, errorCode.getMessage());
        }

    }
}
