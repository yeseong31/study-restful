package com.example.restfulservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @NotBlank
    @Size(min = 2, max = 20, message = "이름은 2 ~ 20자 이내여야 합니다.")
    private String name;

    @Past
    private LocalDateTime joinDate;

    @NotBlank
    @Size(min = 8, max = 20, message = "비밀번호는 8 ~ 20자 이내여야 합니다.")
    private String password;

    @NotBlank
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

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
