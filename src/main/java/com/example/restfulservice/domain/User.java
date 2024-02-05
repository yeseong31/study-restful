package com.example.restfulservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class User {

    private Long id;

    private String name;
    private LocalDateTime joinDate;

    private User(Long id, String name) {
        this.id = id;
        this.name = name;
        this.joinDate = LocalDateTime.now();
    }

    public static User of(Long id, String name) {
        return new User(id, name);
    }
}
