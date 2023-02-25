package com.sparta.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleRequestDto {

    private String date;
    private String title;
    private String author;
    private String contents;
}
