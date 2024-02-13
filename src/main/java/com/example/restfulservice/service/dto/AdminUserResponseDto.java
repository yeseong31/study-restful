package com.example.restfulservice.service.dto;

import com.example.restfulservice.domain.User;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(title = "사용자 ID")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20, message = "이름은 2 ~ 20자 이내여야 합니다.")
    @Schema(title = "사용자 이름")
    private String name;

    @Schema(title = "가입일")
    private LocalDateTime joinDate;

    @Schema(title = "비밀번호")
    private String password;

    @Schema(title = "주민등록번호")
    private String ssn;

    @Schema(title = "사용자 등급")
    private String grade;

    public AdminUserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.joinDate = entity.getJoinDate();
        this.password = entity.getPassword();
        this.ssn = entity.getSsn();
    }
}
