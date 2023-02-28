package com.sparta.schedule.controller;


import com.sparta.schedule.dto.request.MemoRequestDto;
import com.sparta.schedule.dto.response.MemoResponseDto;
import com.sparta.schedule.security.UserDetailsImpl;
import com.sparta.schedule.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule") // 공통 경로
public class MemoController {

    private final MemoService memoService;

    // 메모 조회 API
    @GetMapping("/memo")
    public List<MemoResponseDto> memos(){
        return memoService.memos();
    }

    // 메모 생성 API
    @PostMapping ("/memo")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return memoService.createMemo(memoRequestDto, userDetails);
    }

    // 메모 수정 API
    @PutMapping("/memo/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return memoService.updateMemo(id, memoRequestDto, userDetails);
    }
}