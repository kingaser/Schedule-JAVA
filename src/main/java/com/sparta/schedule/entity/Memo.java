package com.sparta.schedule.entity;


import com.sparta.schedule.dto.MemoRequestDto;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가
    private Long id;

    @Column(nullable = false)
    private String comment;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false) // user_id로 선언된 FK
    private User user;


    public Memo(MemoRequestDto memoRequestDto){
        this.comment = memoRequestDto.getContent();
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    public void update(MemoRequestDto memoRequestDto){
        this.comment = memoRequestDto.getContent();
    }
}
