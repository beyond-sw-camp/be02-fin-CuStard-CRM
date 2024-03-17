package com.example.backend.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionAdvise extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.CUSTOMER_SIGNUP_INCORRECT_EMAIL));
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)                      //TODO: 질문하기
//    public ResponseEntity errorHandler(MethodArgumentNotValidException e) {
//        e.printStackTrace();
//        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.CUSTOMER_SIGNUP_INCORRECT_EMAIL));
//    }


}
