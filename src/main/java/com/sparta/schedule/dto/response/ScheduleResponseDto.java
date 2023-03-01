package com.sparta.schedule.dto.response;

import com.sparta.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String author;
    private String contents;
    private boolean complete;

    @Builder
    public ScheduleResponseDto(Schedule schedule) {
        id = schedule.getId();
        title = schedule.getTitle();
        author = schedule.getAuthor();
        contents = schedule.getContents();
        complete = schedule.isComplete();
    }
}