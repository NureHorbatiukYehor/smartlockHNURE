package com.example.smartlock.controller;

import com.example.smartlock.dto.auth.LoginRequest;
import com.example.smartlock.dto.auth.RegisterRequest;
import com.example.smartlock.dto.auth.UserDto;
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
        return ResponseEntity.ok(null);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(null);
    }

}
