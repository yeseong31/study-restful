package com.example.restfulservice.controller;

import com.example.restfulservice.service.PostService;
import com.example.restfulservice.service.dto.PostResponseDto;
import com.example.restfulservice.service.dto.PostSaveRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "post-controller", description = "게시글 서비스를 위한 컨트롤러")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 목록 조회 API", description = "전체 게시글 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<EntityModel<Map<String, Object>>> findAll() {
        List<PostResponseDto> responseDtoes = postService.findAll();

        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("posts", responseDtoes);
        result.put("count", responseDtoes.size());

        EntityModel<Map<String, Object>> entityModel = EntityModel.of(result);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        entityModel.add(linkTo.withRel("all_posts"));

        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "게시글 등록 API", description = "게시글 정보를 입력받아 게시글 등록을 진행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<PostResponseDto> save(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "게시글 등록 요청 DTO")
            @RequestBody PostSaveRequestDto requestDto) {

        Long savedId = postService.save(requestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "게시글 조회 API", description = "게시글 ID를 통해 게시글 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PostResponseDto>> findById(
            @Parameter(description = "게시글 ID", required = true, example = "1") @PathVariable("id") Long id) {

        PostResponseDto responseDto = postService.findById(id);
        EntityModel<PostResponseDto> entityModel = EntityModel.of(responseDto);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        entityModel.add(linkTo.withRel("post"));

        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "게시글 삭제 API", description = "게시글 ID를 통해 게시글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponseDto> deleteById(
            @Parameter(description = "게시글 ID", required = true, example = "1") @PathVariable("id") Long id) {

        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
