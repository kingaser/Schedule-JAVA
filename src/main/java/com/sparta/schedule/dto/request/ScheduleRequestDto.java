package com.sparta.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleRequestDto {

    private String title;
    private String author;
    private String contents;
    private String date;
}

