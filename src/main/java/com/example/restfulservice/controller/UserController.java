package com.example.restfulservice.controller;

import com.example.restfulservice.service.UserService;
import com.example.restfulservice.service.dto.PostResponseDto;
import com.example.restfulservice.service.dto.UserResponseDto;
import com.example.restfulservice.service.dto.UserSaveRequestDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "user-controller", description = "일반 사용자 서비스를 위한 컨트롤러")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 목록 조회 API", description = "전체 사용자 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<EntityModel<Map<String, Object>>> findAll() {
        List<UserResponseDto> responseDtoes = userService.findAll();

        Map<String, Object> result = new HashMap<>();
        result.put("users", responseDtoes);
        result.put("count", responseDtoes.size());

        EntityModel<Map<String, Object>> entityModel = EntityModel.of(result);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        entityModel.add(linkTo.withRel("all-users"));  // all-users -> http://localhost:8080/users

        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "사용자 회원 가입 API", description = "사용자 정보를 입력받아 회원 가입을 진행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<UserResponseDto> save(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "사용자 회원 가입 요청 DTO")
            @RequestBody UserSaveRequestDto requestDto) {

        Long savedId = userService.save(requestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "사용자 정보 조회 API", description = "사용자 ID를 통해 사용자 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserResponseDto>> findById(
            @Parameter(description = "사용자 ID", required = true, example = "1") @PathVariable("id") Long id) {

        UserResponseDto responseDto = userService.findById(id);
        EntityModel<UserResponseDto> entityModel = EntityModel.of(responseDto);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findById(id));
        entityModel.add(linkTo.withRel("user"));  // user -> http://localhost:8080/user/{id}

        return ResponseEntity.ok(entityModel);
    }

    @Operation(summary = "사용자 삭제 API", description = "사용자 ID를 통해 사용자를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> delete(
            @Parameter(description = "사용자 ID", required = true, example = "1") @PathVariable("id") Long id) {

        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "사용자 ID를 통한 게시글 목록 조회 API", description = "사용자 ID를 통해 게시글 전체 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}/posts")
    public ResponseEntity<EntityModel<Map<String, Object>>> findAllPostsByUserId(
            @Parameter(description = "사용자 ID", required = true, example = "1") @PathVariable("id") Long id) {

        List<PostResponseDto> responseDtoes = userService.findPosts(id);

        Map<String, Object> result = new HashMap<>();
        result.put("posts", responseDtoes);
        result.put("count", responseDtoes.size());

        EntityModel<Map<String, Object>> entityModel = EntityModel.of(result);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        entityModel.add(linkTo.withRel("all-posts"));

        return ResponseEntity.ok(entityModel);
    }
}
