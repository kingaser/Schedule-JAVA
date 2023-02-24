package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
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
    }

}
