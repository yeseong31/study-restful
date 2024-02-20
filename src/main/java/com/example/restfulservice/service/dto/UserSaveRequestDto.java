package com.example.restfulservice.service.dto;

import com.example.restfulservice.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Schema(description = "사용자 회원 가입 요청 DTO")
public class UserSaveRequestDto {

    @Schema(title = "사용자 ID", description = "사용자 ID는 자동으로 생성됩니다.")
    private Long id;

    @Schema(title = "사용자 이름", description = "사용자 이름을 입력합니다.")
    private String name;

    @Schema(title = "비밀번호", description = "사용자의 비밀번호를 입력해 주세요.")
    private String password;

    @Schema(title = "주민등록번호", description = "사용자의 주민등록번호를 입력해 주세요.")
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
