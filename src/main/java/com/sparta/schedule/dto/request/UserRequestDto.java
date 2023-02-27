package com.sparta.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@RequiredArgsConstructor
public class UserRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "아이디는 4~10자리 영문 소문자,숫자를 포함시켜주세요")
    private String username;

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$",message = "비밀번호는 8~15자리 영문 대소문자,숫자를 포함시켜주세요")
    private String password;

}
