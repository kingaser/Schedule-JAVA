package com.sparta.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompleteRequestDto {
    private Long id;
    private boolean isDone;


    public CompleteRequestDto(Long id, boolean isDone) {
        this.id = id;
        this.isDone = isDone;
    }
}