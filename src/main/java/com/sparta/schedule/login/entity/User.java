package com.sparta.schedule.login.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User user_service(String username,String password){
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

}
