package com.example.restfulservice.repository.dto;

import com.example.restfulservice.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = PROTECTED)
public class UserSaveRequestDto {

    private Long id;

    private String name;

    public User toEntity() {
        return User.of(id, name);
    }
}
