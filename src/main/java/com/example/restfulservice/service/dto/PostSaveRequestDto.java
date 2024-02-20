package com.example.restfulservice.service.dto;

import com.example.restfulservice.domain.Post;
import com.example.restfulservice.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Schema(description = "게시글 등록 요청 DTO")
public class PostSaveRequestDto {

    @Schema(title = "게시글 ID", description = "게시글 ID는 자동으로 생성됩니다.")
    private Long id;

    @Schema(title = "게시글 작성자 ID", description = "게시글 작성자 ID를 입력해 주세요.")
    private Long ownerId;

    @Schema(title = "게시글 제목", description = "게시글 제목을 입력해 주세요.")
    private String title;

    @Schema(title = "게시글 본문", description = "게시글 본문을 입력해 주세요.")
    private String content;

    public Post toEntity(final User owner) {
        return Post.createBuilder()
                .id(id)
                .owner(owner)
                .title(title)
                .content(content)
                .build();
    }
}
