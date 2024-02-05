package com.example.restfulservice.repository.dto;

import com.example.restfulservice.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserResponseDto {

    private Long id;

    private String name;
    private LocalDateTime joinDate;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.joinDate = entity.getJoinDate();
    }
}
