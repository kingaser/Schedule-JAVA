package com.sparta.schedule.controller;


import com.sparta.schedule.dto.MemoRequestDto;
import com.sparta.schedule.dto.MemoResponseDto;
import com.sparta.schedule.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule") // 공통 경로
public class memoController {

    private final MemoService memoService;

    // 메모 조회 API
    @GetMapping("/memo")
    public List<MemoResponseDto> memos(){
        return memoService.memos();
    }

    // 메모 생성 API
    @PostMapping ("/memo/create")
    public MemoResponseDto createMemo(MemoRequestDto memoRequestDto){
        return memoService.createMemo(memoRequestDto);
    }

    // 메모 수정 API
    @PutMapping("/memo/update/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, MemoRequestDto memoRequestDto){
        return memoService.updateMemo(id, memoRequestDto);
    }
}