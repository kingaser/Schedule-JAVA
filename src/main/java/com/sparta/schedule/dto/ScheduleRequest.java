package com.sparta.schedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleRequest {

    private LocalDateTime date;
    private String title;
    private String author;
    private String contents;
    private boolean isDone;

}
