package com.sparta.schedule.dto.response;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CompleteResponseDto {
    private String msg;
    private int code;

    private boolean complete;
    @Builder
    public CompleteResponseDto(String msg,boolean complete, int code) {
        this.msg = msg;
        this.complete = complete;
        this.code = code;
    }
    //  HttpStatus 상태 입력으로 Dto 만들기
    public static CompleteResponseDto User_ServiceCode(HttpStatus status,boolean complete, String msg){
        return CompleteResponseDto.builder()
                .code(status.value())
                .complete(complete)
                .msg(msg)
                .build();
    }

    //    code입력으로 Dto 만들기
    public static CompleteResponseDto jwt_filter(int code, boolean complete,String msg){
        return CompleteResponseDto.builder()
                .code(code)
                .complete(complete)
                .msg(msg)
                .build();
    }

}