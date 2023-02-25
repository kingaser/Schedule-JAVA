package com.sparta.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {

    private Long date;
    private String title;
    private String author;
    private String contents;
    private boolean isDone;

}
