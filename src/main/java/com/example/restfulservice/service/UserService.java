package com.example.restfulservice.service;

import com.example.restfulservice.domain.User;
import com.example.restfulservice.exception.UserNotFoundException;
import com.example.restfulservice.repository.UserRepository;
import com.example.restfulservice.repository.dto.UserResponseDto;
import com.example.restfulservice.repository.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity());
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(format("ID[%s] not found", id)));

        return new UserResponseDto(findUser);
    }

    public Long deleteById(Long id) {
        return userRepository.deleteById(id);
    }
}
