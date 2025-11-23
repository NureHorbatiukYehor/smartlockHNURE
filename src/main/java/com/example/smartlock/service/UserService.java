package com.example.smartlock.service;

import com.example.smartlock.dto.auth.UserDto;
import com.example.smartlock.entity.User;
import com.example.smartlock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.OptionalInt;

public class UserService {
    private final UserRepository userRepository;

    @Autowired
    protected UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> saveUser(User user) {

        return Optional.of(userRepository.save(user));

    }

    public Optional<User> getUserByEmail(String email) {
        return (userRepository.findByEmail(email));
    }
}
