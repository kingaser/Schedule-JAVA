package com.sparta.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private String email;

//    @Column(nullable = false)
//    private Long kakaoId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;



    @Builder
    public User(String username, String password, String email, Long kakaoId, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
//        this.kakaoId = kakaoId;
        this.role = role;
    }





    public static User user_service(String username, String password, String email, UserRoleEnum role){
        return User.builder()
                .username(username)
                .password(password)
                .role(role)
                .email(email)
                .build();
                }

//    public User kakaoIdUpdate(Long kakaoId){
//        this.kakaoId = kakaoId;
//        return this;
//    }

}
