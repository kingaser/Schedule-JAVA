package com.sparta.schedule.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.schedule.dto.MegResponseDto;
import com.sparta.schedule.dto.UserRequestDto;
import com.sparta.schedule.jwt.JwtUtil;
import com.sparta.schedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user",produces = "application/json")
public class UserController {

    private final UserService userService;
//    private final KakaoService kakaoService;


    @PostMapping("/login")
    public ResponseEntity<MegResponseDto>login(@RequestBody UserRequestDto userRequestDto){
        return userService.login(userRequestDto);
    }


    @PostMapping("/signup")
    public ResponseEntity<MegResponseDto>signup(@Valid @RequestBody UserRequestDto userRequestDto){
        return userService.signup(userRequestDto);
    }


//    @PostMapping("/kakao/login")
//    public String kakaoLogin(@RequestParam String code, HttpServletResponse response)throws JsonProcessingException{
////        code : 카카오 서버로부터 받은 인가 코드
//        String createToken = kakaoService.kakaoLogin(code, response);
//
////        Cookie 생성 및 직접 브라우저에 Set
//        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, createToken.substring(7));
//        cookie.setPath("/");
//        response.addCookie(cookie);

//        return "redirect:/schedule";
//    }


}