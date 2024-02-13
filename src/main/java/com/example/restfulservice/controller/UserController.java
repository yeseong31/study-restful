package com.example.restfulservice.controller;

import com.example.restfulservice.exception.UserNotFoundException;
import com.example.restfulservice.service.UserService;
import com.example.restfulservice.service.dto.UserResponseDto;
import com.example.restfulservice.service.dto.UserSaveRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public CollectionModel<UserResponseDto> findAll() {
        List<UserResponseDto> responseDtos = userService.findAll();
        CollectionModel<UserResponseDto> entityModel = CollectionModel.of(responseDtos);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        entityModel.add(linkTo.withRel("all-users"));  // all-users -> http://localhost:8080/users

        return entityModel;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserSaveRequestDto requestDto) {
        Long savedId = userService.save(requestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public EntityModel<UserResponseDto> findById(@PathVariable("id") Long id) {
        UserResponseDto responseDto = userService.findById(id);
        EntityModel<UserResponseDto> entityModel = EntityModel.of(responseDto);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findById(id));
        entityModel.add(linkTo.withRel("user"));  // user -> http://localhost:8080/user/{id}

        return entityModel;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Long deletedId = userService.deleteById(id);

        if (deletedId == null) {
            throw new UserNotFoundException(format("ID[%s] not found", id));
        }

        return ResponseEntity.noContent().build();
    }
}
