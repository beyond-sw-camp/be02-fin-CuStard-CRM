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
    public BaseResponse baseException(BaseException baseException) {

        return BaseResponse.failResponse(baseException.getBaseResponseStatus());
    }
    @ExceptionHandler(ResponseStatusException.class)
    public BaseResponse httpException(ResponseStatusException e) {  //  403

        if (e.getRawStatusCode()==401){
            return BaseResponse.failResponse(BaseResponseStatus.UNAUTHORIZED);
        }else if(e.getRawStatusCode()==403){
            return BaseResponse.failResponse(BaseResponseStatus.FORBIDDEN);
        }else {
            return BaseResponse.failResponse(BaseResponseStatus.UNEXPECTED_ERROR);
        }
    }
    @ExceptionHandler(MalformedJwtException.class)
    public BaseResponse malformedJwtException(MalformedJwtException e){
        return BaseResponse.failResponse(BaseResponseStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_SUPPORT_METHOD));
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("aaa");
        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_FOUND));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        System.out.println(ex.getFieldErrors().get(0).getField());
//        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);

        if (fieldError.getField().equals("customerEmail")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_AVAILABLE_EMAIL));
        }else if (fieldError.getField().equals("customerPwd")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_AVALIABLE_PASSWORD));
        }else if(fieldError.getField().equals("impUid")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.ORDERS_VALIDATION_EMPTY_IMPUID));
        }else if(fieldError.getField().equals("title")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.QNA_REGISTER_EMPTY_TITLE));
        }else if(fieldError.getField().equals("qnaContent")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.QNA_REGISTER_EMPTY_QNACONTENT));
        }else if(fieldError.getField().equals("qnaPwd")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.QNA_REGISTER_INCORRECT_PASSWORD));
        }
        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.UNEXPECTED_ERROR));
    }
}
