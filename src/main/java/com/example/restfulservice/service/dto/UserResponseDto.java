package com.example.restfulservice.service.dto;

import com.example.restfulservice.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@JsonIgnoreProperties(value = {"password", "ssn"})
public class UserResponseDto {

    private Long id;

    private String name;
    private LocalDateTime joinDate;
    private String password;
    private String ssn;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.joinDate = entity.getJoinDate();
        this.password = entity.getPassword();
        this.ssn = entity.getSsn();
    }
}
