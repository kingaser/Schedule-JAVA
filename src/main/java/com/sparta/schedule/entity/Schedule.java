package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private String title;

    private String author;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @Builder
    public Schedule(ScheduleRequestDto scheduleRequestDto, User user) {
        date = scheduleRequestDto.getDate();
        title = scheduleRequestDto.getTitle();
        author = scheduleRequestDto.getAuthor();
        contents = scheduleRequestDto.getContents();
        this.user = user;
    }

    public void update(ScheduleRequestDto scheduleRequestDto, User user) {
        this.title = scheduleRequestDto.getTitle();
        this.contents = scheduleRequestDto.getContents();
        this.user = user;
    }
}
