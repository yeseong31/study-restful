package com.example.restfulservice.repository.dto;

import com.example.restfulservice.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class UserSaveRequestDto {

    private Long id;
    private String name;

    public User toEntity() {
        return User.createBuilder()
                .id(id)
                .name(name)
                .build();
    }
}
