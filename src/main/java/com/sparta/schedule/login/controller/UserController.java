package com.sparta.schedule.login.controller;


import com.sparta.schedule.login.service.UserService;
import com.sparta.schedule.login.dto.MegResponseDto;
import com.sparta.schedule.login.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<MegResponseDto>login(@RequestBody UserRequestDto userRequestDto){
        return userService.login(userRequestDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<MegResponseDto>signup(@Valid @RequestBody UserRequestDto userRequestDto){
        return userService.signup(userRequestDto);
    }

}
