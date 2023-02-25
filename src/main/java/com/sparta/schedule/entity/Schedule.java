package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(Schedule.class)
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    private Long date;
    private String title;
    private String author;
    private String contents;
    private boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @Builder
    public Schedule(ScheduleRequestDto scheduleRequest, User user) {
        title = scheduleRequest.getTitle();
        author = scheduleRequest.getAuthor();
        contents = scheduleRequest.getContents();
        date = scheduleRequest.getDate();
        isDone = scheduleRequest.isDone();
        this.user = user;
    }

    public void update(ScheduleRequestDto scheduleRequestDto, User user) {
        title = scheduleRequestDto.getTitle();
        contents = scheduleRequestDto.getContents();
        this.user = user;
    }

    public void updateStatus(boolean isDone) {
        this.isDone = isDone;
    }

}
