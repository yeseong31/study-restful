package com.example.restfulservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
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
