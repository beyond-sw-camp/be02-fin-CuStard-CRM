package com.example.backend_admin.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_SUPPORT_METHOD));
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_FOUND));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        System.out.println(ex.getFieldErrors().get(0).getField());
//        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);

        if (fieldError.getField().equals("adminEmail")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_AVAILABLE_EMAIL));
        }else if (fieldError.getField().equals("adminPwd")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.NOT_AVALIABLE_PASSWORD));
        }else if(fieldError.getField().equals("targetList")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.CAN_NOT_FIND_TARGETLIST));
        }else if(fieldError.getField().equals("discount")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.COUPON_CREATE_EMPTY_DISCOUNT));
        }else if(fieldError.getField().equals("couponCategory")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.COUPON_CREATE_EMPTY_CATEGORY));
        }else if(fieldError.getField().equals("answerContent")){
            return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.QNA_REGISTER_INCORRECT_PASSWORD));
        }
        return ResponseEntity.ok().body(BaseResponse.failResponse(BaseResponseStatus.UNEXPECTED_ERROR));
    }
}
