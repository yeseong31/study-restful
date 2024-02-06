package com.example.restfulservice.controller;

import com.example.restfulservice.exception.UserNotFoundException;
import com.example.restfulservice.repository.UserRepository;
import com.example.restfulservice.repository.dto.UserResponseDto;
import com.example.restfulservice.repository.dto.UserSaveRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<UserResponseDto> findAll() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserSaveRequestDto requestDto) {
        Long savedId = userRepository.save(requestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(format("ID[%s] not found", id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Long deletedId = userRepository.deleteById(id);

        if (deletedId == null) {
            throw new UserNotFoundException(format("ID[%s] not found", id));
        }

        return ResponseEntity.noContent().build();
    }
}
