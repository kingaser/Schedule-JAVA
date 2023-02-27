package com.sparta.schedule.dto.response;

import com.sparta.schedule.entity.CalendarDate;
import com.sparta.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CalendarDateResponseDto {

    private String date;
    private List<Schedule> scheduleList;

    @Builder
    public CalendarDateResponseDto(CalendarDate calendarDate) {
        date = calendarDate.getDate();
        scheduleList = calendarDate.getSchedules();
    }

//    public CalendarDateResponseDto(CalendarDateResponseDto calendarDateResponseDto) {
//        scheduleList = calendarDateResponseDto.getScheduleList();
//    }
}
