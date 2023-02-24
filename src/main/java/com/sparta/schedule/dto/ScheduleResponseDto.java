package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {

    private String title;
    private String author;
    private String contents;

    @Builder
    private ScheduleResponseDto(Schedule schedule) {
        title = schedule.getTitle();
        author = schedule.getAuthor();
        contents = schedule.getContents();
    }

    public static ScheduleResponseDto from(Schedule schedule) {
        return ScheduleResponseDto.builder()
                .schedule(schedule)
                .build();
    }

}
