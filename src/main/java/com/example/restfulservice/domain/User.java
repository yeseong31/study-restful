package com.example.restfulservice.domain;

import lombok.Builder;
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
    private String password;
    private String ssn;

    private User(Long id, String name, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = LocalDateTime.now();
        this.password = password;
        this.ssn = ssn;
    }

    @Builder(builderMethodName = "createBuilder")
    public static User of(Long id, String name, String password, String ssn) {
        return new User(id, name, password, ssn);
    }
}
