package com.sparta.schedule.login.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private final ErrorCode errorcode;

    public ApiException(ErrorCode Errorcode){
        this.errorcode = getErrorcode();
    }
}
