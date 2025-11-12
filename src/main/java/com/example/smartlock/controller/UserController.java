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


    @PostMapping("/role")
    public ResponseEntity<LockRoleResponse> addUserToLock(@RequestBody LockRoleRequest lockRoleRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/role")
    public ResponseEntity<LockRoleResponse> deleteUserFromLock(@RequestBody LockRoleRequest lockRoleRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/role")
    public ResponseEntity<LockRoleResponse> changeUserLockRole(@RequestBody LockRoleRequest lockRoleRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }



}
