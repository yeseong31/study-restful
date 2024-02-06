package com.example.restfulservice.service.dto;

import com.example.restfulservice.domain.User;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonFilter("UserInfo")
@JsonIgnoreProperties(value = {"password",})
public class AdminUserResponseDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20, message = "이름은 2 ~ 20자 이내여야 합니다.")
    private String name;

    private LocalDateTime joinDate;
    private String password;
    private String ssn;

    public AdminUserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.joinDate = entity.getJoinDate();
        this.password = entity.getPassword();
        this.ssn = entity.getSsn();
    }
}
