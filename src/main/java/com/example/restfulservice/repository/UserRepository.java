package com.example.restfulservice.repository;

import com.example.restfulservice.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Long save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    Long deleteById(Long id);
}
