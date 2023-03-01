package com.sparta.schedule.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.schedule.dto.request.UserRequestDto;
import com.sparta.schedule.dto.response.MessageResponseDto;
import com.sparta.schedule.dto.response.UserResponseDto;
import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.service.KakaoService;
import com.sparta.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user",produces = "application/json")
public class UserController {

    private final UserService userService;
    private final KakaoService kakaoService;



    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto userRequestDto){
        return userService.login(userRequestDto);
    }

    @PostMapping("/idCheck")
    public ResponseEntity<MessageResponseDto> idCheck(@RequestBody UserRequestDto userRequestDto){
        return userService.idCheck(userRequestDto);
    }


    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto>signup(@Valid @RequestBody UserRequestDto userRequestDto){
        return userService.signup(userRequestDto);
    }


    @PostMapping("/kakao/login")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response)throws JsonProcessingException {
//        code : 카카오 서버로부터 받은 인가 코드
        String createToken = kakaoService.kakaoLogin(code, response);

//        Cookie 생성 및 직접 브라우저에 Set
        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/15.164.158.158:8080/date";
    }


}