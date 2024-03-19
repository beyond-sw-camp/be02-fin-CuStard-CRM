package com.example.backend_admin.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception{
    private BaseResponseStatus baseResponseStatus;
}
