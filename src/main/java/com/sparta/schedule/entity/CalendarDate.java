package com.sparta.schedule.entity;

import com.sparta.schedule.dto.request.CalendarDateRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarDate {

    @Id
    @Column(unique = true, nullable = false)
    private String date;


    @OneToMany
    private List<Schedule> schedules;

    @Builder
    public CalendarDate(CalendarDateRequestDto calendarDateRequestDto, List<Schedule> scheduleList) {
        date = calendarDateRequestDto.getDate();
        schedules = scheduleList;
    }
}
