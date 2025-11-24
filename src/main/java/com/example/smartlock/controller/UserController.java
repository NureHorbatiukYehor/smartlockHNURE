package com.example.smartlock.controller;

import com.example.smartlock.dto.LockRoleRequest;
import com.example.smartlock.dto.LockRoleResponse;
import com.example.smartlock.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(null);
    }






}
