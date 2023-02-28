package com.sparta.schedule.dto.response;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponseDto {
    private String msg;
    private int code;

    @Builder
    public MessageResponseDto(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
    //  HttpStatus 상태 입력으로 Dto 만들기
    public static MessageResponseDto User_ServiceCode(HttpStatus status, String msg){
        return MessageResponseDto.builder()
                .code(status.value())
                .msg(msg)
                .build();
    }

//    code입력으로 Dto 만들기
    public static MessageResponseDto jwt_filter(int code, String msg){
        return MessageResponseDto.builder()
                .code(code)
                .msg(msg)
                .build();
    }



}
