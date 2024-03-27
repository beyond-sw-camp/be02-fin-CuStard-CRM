package com.example.backend.common;

import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity baseException(BaseException baseException) {

        return ResponseEntity.ok().body(BaseResponse.failResponse(baseException.getBaseResponseStatus()));
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity authorizedException(ResponseStatusException e) {
//        HttpClientErrorException.Unauthorized.class
//        ex.getBindingResult().getFieldError().getField();
        System.out.println(e.getResponseHeaders());
        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.UNAUTHORIZED));
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity authenticationException(AuthenticationException e) {

        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.ADMIN_ANSWER_EMPTY_TOKEN));
    }
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity notFound(NoHandlerFoundException e) {
//
//        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_FOUND));
//    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_SUPPORT_METHOD));
    }

//    @ExceptionHandler(HttpClientErrorException.class)
//    public ResponseEntity authorizedException(HttpClientErrorException e) {
//
//        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.UNAUTHORIZED));
//    }



    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("aaa");
        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_FOUND));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ex.printStackTrace();
        System.out.println(ex.getMessage());

//        System.out.println(ex.getBindingResult().getFieldError().getField());
//        System.out.println(ex.getBindingResult().getFieldError().getDefaultMessage());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError:fieldErrors) {
            System.out.println(fieldError.getField());
        }
        for (FieldError fieldError:fieldErrors) {
            System.out.println(fieldError.getDefaultMessage());
        }
//        System.out.println("header : "  + headers);
//        System.out.println("status : "  + status);
//        System.out.println("request : "  + request);
//        System.out.println(ex.getParameter());



        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_FOUND));
    }
}
