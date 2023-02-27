package com.sparta.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@RequiredArgsConstructor
public class UserRequestDto {


    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$",message = "비밀번호는 8~15자리 영문 대소문자,숫자를 포함시켜주세요")
    private String password;

    private String username;
}
