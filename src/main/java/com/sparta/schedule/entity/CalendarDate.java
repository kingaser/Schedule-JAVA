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

    @OneToMany(mappedBy = "calendarDate", cascade = CascadeType.ALL)
    private List<Schedule> scheduleList;

    @Builder
    public CalendarDate(CalendarDateRequestDto calendarDateRequestDto) {
        date = calendarDateRequestDto.getDate();
    }

    @Builder
    public CalendarDate(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
