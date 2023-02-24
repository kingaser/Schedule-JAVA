package com.sparta.schedule.login.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;

@Getter
@RequiredArgsConstructor
public class UserRequestDto {
    @Pattern(regexp = "^[A-z0-9]{3,9}$", message = "아이디는 4~10자리 영문 대소문자 숫자를 포함시켜주세요")
    private String username;
    @Pattern(regexp = "^[A-z0-9!@#$%^&*()_+]{7,14}$",message = "비밀번호는 8~15자리 영문 대소문자,숫자,특수문자를 포함시켜주세요")
    private String password;
}
