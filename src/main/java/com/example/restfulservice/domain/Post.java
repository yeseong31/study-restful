package com.example.restfulservice.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    private User owner;

    private String title;

    @Lob
    private String content;

    private Post(Long id, User owner, String title, String content) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.content = content;
    }

    @Builder(builderMethodName = "createBuilder")
    public static Post of(Long id, User owner, String title, String content) {
        return new Post(id, owner, title, content);
    }
}
