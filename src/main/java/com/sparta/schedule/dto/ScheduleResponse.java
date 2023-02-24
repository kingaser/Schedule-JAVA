package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleResponse {

    private String title;
    private String author;
    private String contents;

    @Builder
    private ScheduleResponse(Schedule schedule) {
        title = schedule.getTitle();
        author = schedule.getAuthor();
        contents = schedule.getContents();
    }

    public static ScheduleResponse from(Schedule schedule) {
        return ScheduleResponse.builder()
                .schedule(schedule)
                .build();
    }

}
