package com.example.smartlock.controller;

import com.example.smartlock.model.dto.lockrole.LockRoleDto;
import com.example.smartlock.model.enums.UserRole;
import com.example.smartlock.service.LockRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/locks/{lockId}/roles/{userId}")
public class LockRoleController {
    LockRoleService lockAccessService;

    public LockRoleController(LockRoleService lockAccessService) {
        this.lockAccessService = lockAccessService;
    }

    @PreAuthorize("@lockGuard.check(#lockId, 'ADMIN', 'OWNER')")
    @PostMapping
    public ResponseEntity<LockRoleDto> addUserToLock(
            @RequestBody UserRole userRole,
            @PathVariable UUID lockId,
            @PathVariable UUID userId) {

        LockRoleDto lockAccessDto = lockAccessService.addUserToLock(userRole, lockId, userId);
        return ResponseEntity.ok(lockAccessDto);
    }

    @PreAuthorize("@lockGuard.check(#lockId, 'ADMIN', 'OWNER')")
    @DeleteMapping
    public ResponseEntity<Void> deleteUserFromLock( @PathVariable UUID lockId, @PathVariable UUID userId) {
        lockAccessService.deleteUserFromLock(userId, lockId);
        return ResponseEntity.ok(null);
    }

    @PreAuthorize("@lockGuard.check(#lockId, 'OWNER')")
    @PutMapping
    public ResponseEntity<LockRoleDto> changeUserLockRole(
            @RequestBody UserRole lockRole,
            @PathVariable UUID lockId,
            @PathVariable UUID userId) {


       LockRoleDto lockRoleDto = lockAccessService.changeUserLockRole(userId, lockId, lockRole);
        return ResponseEntity.ok(lockRoleDto);
    }
}
