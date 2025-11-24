package com.example.smartlock.controller;

import com.example.smartlock.dto.lockrole.LockRoleRequest;
import com.example.smartlock.dto.lockrole.LockRoleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class LockAccessController {
    @PostMapping()
    public ResponseEntity<LockRoleResponse> addUserToLock(@RequestBody LockRoleRequest lockRoleRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<LockRoleResponse> deleteUserFromLock(@RequestBody LockRoleRequest lockRoleRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<LockRoleResponse> changeUserLockRole(@RequestBody LockRoleRequest lockRoleRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }
}
