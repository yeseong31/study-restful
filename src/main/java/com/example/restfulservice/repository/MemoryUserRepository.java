package com.example.restfulservice.repository;

import com.example.restfulservice.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryUserRepository implements UserRepository {

    private static final Map<Long, User> users = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Long save(User user) {
        user.setId(++sequence);
        users.put(sequence, user);
        return sequence;
    }

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        if (!users.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Long deleteById(Long id) {
        if (!users.containsKey(id)) {
            return null;
        }

        users.remove(id);
        return id;
    }
}
