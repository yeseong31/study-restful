package com.example.restfulservice.service;

import com.example.restfulservice.domain.Post;
import com.example.restfulservice.domain.User;
import com.example.restfulservice.exception.PostNotFoundException;
import com.example.restfulservice.exception.UserNotFoundException;
import com.example.restfulservice.repository.JpaPostRepository;
import com.example.restfulservice.repository.JpaUserRepository;
import com.example.restfulservice.service.dto.PostResponseDto;
import com.example.restfulservice.service.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final JpaPostRepository postRepository;
    private final JpaUserRepository userRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        Long ownerId = requestDto.getOwnerId();

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new UserNotFoundException(format("USER ID[%s] not found", ownerId)));

        return postRepository.save(requestDto.toEntity(owner)).getId();
    }

    public List<PostResponseDto> findAll() {
        return postRepository.findAll()
                .stream()
                .map(PostResponseDto::new)
                .toList();
    }

    public PostResponseDto findById(Long id) {
        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(format("ID[%s] not found", id)));

        return new PostResponseDto(findPost);
    }

    @Transactional
    public void deleteById(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(format("ID[%s] not found", id)));

        postRepository.deleteById(id);
    }
}
