package com.sparta.schedule.entity;


import com.sparta.schedule.dto.MemoRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false) // user_id로 선언된 FK
    private User user;

    @Builder
    public Memo(MemoRequestDto memoRequestDto, User user){
        this.content = memoRequestDto.getContent();
        this.user = user;
    }

    public void update(MemoRequestDto memoRequestDto, User user){
        this.content = memoRequestDto.getContent();
        this.user = user;
    }

}
