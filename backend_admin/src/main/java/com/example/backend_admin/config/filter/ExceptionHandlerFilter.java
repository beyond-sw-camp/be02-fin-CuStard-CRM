package com.example.backend_admin.config.filter;


import com.example.backend_admin.admin.model.response.FilterErrorResponse;
import com.example.backend_admin.common.BaseResponseStatus;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, request, response, e);
        }

    }

    public void setErrorResponse(HttpStatus status, HttpServletRequest request,
                                 HttpServletResponse response, Throwable ex) throws IOException {

        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(FilterErrorResponse.builder()
                .isSuccess(false)
                .code(BaseResponseStatus.UNAUTHORIZED.getCode())
                .message(BaseResponseStatus.UNAUTHORIZED.getMessage())
                .build().convertToJson());

    }

}
