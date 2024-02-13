package com.example.restfulservice.service.dto;

import com.example.restfulservice.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@JsonIgnoreProperties(value = {"password", "ssn"})
@Schema(description = "사용자 응답 DTO")
public class UserResponseDto {

    @Schema(title = "사용자 ID")
    private Long id;

    @Schema(title = "사용자 이름")
    private String name;

    @Schema(title = "가입일")
    private LocalDateTime joinDate;

    @Schema(title = "비밀번호")
    private String password;

    @Schema(title = "주민등록번호")
    private String ssn;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.joinDate = entity.getJoinDate();
        this.password = entity.getPassword();
        this.ssn = entity.getSsn();
    }
}
