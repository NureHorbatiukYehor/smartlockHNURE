package com.example.smartlock.controller;

import com.example.smartlock.dto.LoginRequest;
import com.example.smartlock.dto.RegisterRequest;
import com.example.smartlock.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody RegisterRequest registerRequest){
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginRequest loginRequest) {
        return null;
    }

}
