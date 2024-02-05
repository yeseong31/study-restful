package com.example.restfulservice.repository;

import com.example.restfulservice.domain.User;
import com.example.restfulservice.repository.dto.UserResponseDto;
import com.example.restfulservice.repository.dto.UserSaveRequestDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserMemoryRepository implements UserRepository {

    private static final Map<Long, User> users = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Long save(UserSaveRequestDto requestDto) {
        requestDto.setId(++sequence);
        users.put(
                requestDto.getId(),
                requestDto.toEntity());
        return sequence;
    }

    @Override
    public List<UserResponseDto> findAll() {
        return users.values().stream()
                .map(UserResponseDto::new)
                .toList();
    }

    @Override
    public Optional<UserResponseDto> findById(Long id) {
        if (!users.containsKey(id)) {
            return Optional.empty();
        }

        User findUser = users.get(id);
        return Optional.of(new UserResponseDto(findUser));
    }
}
