package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequest;
import com.sparta.schedule.login.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    private LocalDateTime date;

    private String title;
    private String author;
    private String contents;
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private Schedule(ScheduleRequest scheduleRequest, User user) {
        title = scheduleRequest.getTitle();
        author = scheduleRequest.getAuthor();
        contents = scheduleRequest.getContents();
        isDone = scheduleRequest.isDone();
        this.user = user;
    }

    public static Schedule of(ScheduleRequest scheduleRequest, User user) {
        return Schedule.builder()
                .scheduleRequest(scheduleRequest)
                .user(user)
                .build();
    }

}
