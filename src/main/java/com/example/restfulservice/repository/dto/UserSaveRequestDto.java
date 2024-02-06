package com.example.restfulservice.repository.dto;

import com.example.restfulservice.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@JsonIgnoreProperties(value = {"password", "ssn"})
public class UserSaveRequestDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20, message = "이름은 2 ~ 20자 이내여야 합니다.")
    private String name;

    private String password;
    private String ssn;

    public User toEntity() {
        return User.createBuilder()
                .id(id)
                .name(name)
                .password(password)
                .ssn(ssn)
                .build();
    }
}
