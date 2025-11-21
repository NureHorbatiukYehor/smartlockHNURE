package com.example.smartlock.service;

import com.example.smartlock.dto.auth.RegisterRequest;
import com.example.smartlock.dto.auth.UserDto;
import com.example.smartlock.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    protected AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserDto registerUser(RegisterRequest request) {

        String passwordHash = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getEmail(),
                passwordHash,
                request.getFullName(),
                OffsetDateTime.now()
        );

        if (userService.saveUser(user).isPresent()){
            String token = jwtService.generateToken(user.getEmail());

            UserDto userDto = new UserDto(
                    token,
                    user.getEmail(),
                    user.getFullName()
            );

            return userDto;
        } else {
            throw new RuntimeException() ;
        }

    }
}
