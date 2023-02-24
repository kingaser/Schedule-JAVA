package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Memo;

public class MemoResponseDto {
    private String content;

    public MemoResponseDto(Memo memo){
        this.content = memo.getComment();
    }
}
