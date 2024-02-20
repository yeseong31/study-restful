package com.example.restfulservice.service.dto;

import com.example.restfulservice.domain.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Schema(description = "게시글 응답 DTO")
public class PostResponseDto {

    @Schema(title = "게시글 ID")
    private Long id;

    @Schema(title = "게시글 작성자 정보")
    private UserResponseDto owner;

    @Schema(title = "게시글 제목")
    private String title;

    @Schema(title = "게시글 본문")
    private String content;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.owner = new UserResponseDto(entity.getOwner());
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
