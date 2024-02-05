package com.example.restfulservice.repository;

import com.example.restfulservice.domain.User;
import com.example.restfulservice.repository.dto.UserResponseDto;
import com.example.restfulservice.repository.dto.UserSaveRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Long save(UserSaveRequestDto requestDto);

    List<UserResponseDto> findAll();

    Optional<UserResponseDto> findById(Long id);

    Long deleteById(Long id);
}
