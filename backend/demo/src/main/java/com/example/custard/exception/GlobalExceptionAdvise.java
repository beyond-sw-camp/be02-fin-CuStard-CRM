package com.example.custard.exception;


import com.example.custard.exception.exception.CourseException;
import com.example.custard.exception.exception.MemberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionAdvise extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvise.class);
    @ExceptionHandler(MemberException.class)
    public ResponseEntity handleMemberException(MemberException e) {
        log.error("MemberException : [{}] - {}", e.getErrorCode().getStatus(), e.getMessage());
        return makeResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(CourseException.class)
    public ResponseEntity handleCourseException(CourseException e) {
        log.error("CourseException : [{}] - {}", e.getErrorCode().getStatus(), e.getMessage());
        return makeResponseEntity(e.getErrorCode());
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return makeResponseEntity(ErrorCode.INVALID_INPUT);
    }

    private ResponseEntity makeResponseEntity(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus()).body(
                ErrorResponse.builder()
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
        );
    }

    

}
