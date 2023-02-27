package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoResponseDto {
    private Long id;
    private String content;
    private String username;

    public MemoResponseDto(Memo memo){
        this.id = memo.getId();
        this.content = memo.getContent();
        this.username = memo.getUser().getUsername();
    }
}
