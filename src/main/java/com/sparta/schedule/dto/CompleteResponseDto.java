package com.sparta.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompleteResponseDto {
    private String msg;

    public CompleteResponseDto(String msg) {
        this.msg = msg;
    }
}
