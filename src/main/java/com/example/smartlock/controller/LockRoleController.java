package com.example.smartlock.controller;

import com.example.smartlock.dto.lockrole.LockRoleDto;
import com.example.smartlock.entity.CustomUserDetails;
import com.example.smartlock.enums.UserRole;
import com.example.smartlock.service.LockRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/locks/{lockId}/roles/{userId}")
public class LockRoleController {
    LockRoleService lockAccessService;

    public LockRoleController(LockRoleService lockAccessService) {
        this.lockAccessService = lockAccessService;
    }

    @PostMapping
    public ResponseEntity<LockRoleDto> addUserToLock(
            @RequestBody LockRoleDto lockRoleRequest,
            @PathVariable UUID lockId,
            @PathVariable UUID userId,
            Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID actorUserId = userDetails.getId();

        LockRoleDto lockAccessDto = lockAccessService.addUserToLock(lockRoleRequest, lockId, userId, actorUserId);
        return ResponseEntity.ok(lockAccessDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserFromLock(
            @PathVariable UUID lockId,
            @PathVariable UUID userId,
            Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID actorUserId = userDetails.getId();

        lockAccessService.deleteUserFromLock(userId, lockId, actorUserId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<LockRoleDto> changeUserLockRole(
            @RequestBody UserRole lockRole,
            @PathVariable UUID lockId,
            @PathVariable UUID userId,
            Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID actorUserId = userDetails.getId();

        lockAccessService.changeUserLockRole(userId, lockId, actorUserId, lockRole);
        return ResponseEntity.ok(null);
    }
}
