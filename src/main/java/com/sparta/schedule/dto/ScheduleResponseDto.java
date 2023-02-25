package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private String title;
    private String author;
    private String contents;

    @Builder
    public ScheduleResponseDto(Schedule schedule) {
        title = schedule.getTitle();
        author = schedule.getAuthor();
        contents = schedule.getContents();
    }
}