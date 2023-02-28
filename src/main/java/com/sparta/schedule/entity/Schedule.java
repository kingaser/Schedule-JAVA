package com.sparta.schedule.entity;

import com.sparta.schedule.dto.request.CalendarDateRequestDto;
import com.sparta.schedule.dto.request.CompleteRequestDto;
import com.sparta.schedule.dto.request.ScheduleRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.stream.Stream;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String contents;

    private boolean complete;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "calendardate_id", nullable = false)
    private CalendarDate calendarDate;

    @Builder
    public Schedule(ScheduleRequestDto scheduleRequestDto, CalendarDate calendarDate, User user) {
        title = scheduleRequestDto.getTitle();
        author = scheduleRequestDto.getAuthor();
        contents = scheduleRequestDto.getContents();
        this.calendarDate = calendarDate;
        this.user = user;
    }

    public void update(ScheduleRequestDto scheduleRequestDto, User user) {
        title = scheduleRequestDto.getTitle();
        contents = scheduleRequestDto.getContents();
        this.user = user;
    }

    public void updateCompleteStatus(CompleteRequestDto requestDto) {
        this.complete = requestDto.isComplete();
    }

}
