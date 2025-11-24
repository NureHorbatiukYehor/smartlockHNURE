package com.example.smartlock.controller;

import com.example.smartlock.dto.lockrole.LockAccessDto;
import com.example.smartlock.service.LockAccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class LockAccessController {
    LockAccessService lockAccessService;

    public LockAccessController(LockAccessService lockAccessService) {
        this.lockAccessService = lockAccessService;
    }

    @PostMapping()
    public ResponseEntity<LockAccessDto> addUserToLock(@RequestBody LockAccessDto lockRoleRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<LockAccessDto> deleteUserFromLock(@RequestBody LockAccessDto lockRoleRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<LockAccessDto> changeUserLockRole(@RequestBody LockAccessDto lockRoleRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }
}
