package com.sparta.schedule.dto.response;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserResponseDto {
    private String msg;
    private int code;
    private String username;

    @Builder
    public UserResponseDto(String msg, int code, String username) {
        this.msg = msg;
        this.username = username;
        this.code = code;
    }
    //  HttpStatus 상태 입력으로 Dto 만들기
    public static UserResponseDto User_ServiceCode(HttpStatus status, String msg, String username){
        return UserResponseDto.builder()
                .code(status.value())
                .username(username)
                .msg(msg)
                .build();
    }

//    code입력으로 Dto 만들기
    public static UserResponseDto jwt_filter(int code, String msg, String username){
        return UserResponseDto.builder()
                .code(code)
                .msg(msg)
                .build();
    }



}
