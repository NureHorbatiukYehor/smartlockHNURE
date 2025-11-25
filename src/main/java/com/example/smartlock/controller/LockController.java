package com.example.smartlock.controller;

import com.example.smartlock.model.dto.lock.CreateLockRequest;
import com.example.smartlock.model.dto.lock.EditLockRequest;
import com.example.smartlock.model.dto.lock.LockDto;
import com.example.smartlock.model.entity.CustomUserDetails;
import com.example.smartlock.model.enums.UserRole;
import com.example.smartlock.service.LockRoleService;
import com.example.smartlock.service.LockService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/locks")
public class LockController {
    private final LockService lockService;
    private final LockRoleService lockRoleService;

    public LockController(LockService lockService, LockRoleService lockAccessService) {
        this.lockService = lockService;
        this.lockRoleService = lockAccessService;
    }

    @GetMapping
    public ResponseEntity<List<LockDto>> getAllLocksByUserId(Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        List<LockDto> lockDtos = lockRoleService.getAllLocksByUserId(userId);

        return ResponseEntity.ok(lockDtos);
    }

    @PostMapping
    public ResponseEntity<LockDto> createLock(@RequestBody CreateLockRequest createLockRequest, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        LockDto lockDto = lockService.createLock(createLockRequest, userId);
        lockRoleService.addUserToLock(UserRole.OWNER, userId, lockDto.getLockId());
        return ResponseEntity.ok(lockDto);
    }

    @PreAuthorize("@lockGuard.check(#lockId, 'OWNER')")
    @DeleteMapping("/{lockId}")
    public ResponseEntity<LockDto> deleteLock(@PathVariable UUID lockId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        lockService.deleteLock(lockId, userId);
        return ResponseEntity.ok(null);
    }

    @PreAuthorize("@lockGuard.check(#lockId, 'ADMIN', 'OWNER')")
    @PutMapping("/{lockId}")
    public ResponseEntity<LockDto> editLock(@RequestBody EditLockRequest request, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        LockDto lockDto = lockService.editLock(request, userId);
        return ResponseEntity.ok(lockDto);
    }

    @PreAuthorize("@lockGuard.check(#lockId, 'GUEST', 'MEMBER', 'ADMIN', 'OWNER')")
    @PutMapping("/{lockId}/lock")
    public ResponseEntity<LockDto> lockLock(@PathVariable UUID lockId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        LockDto lockDto = lockService.lockLock(lockId, userId);
        return ResponseEntity.ok(lockDto);
    }

    @PreAuthorize("@lockGuard.check(#lockId, 'GUEST', 'MEMBER', 'ADMIN', 'OWNER')")
    @PutMapping("/{lockId}/unlock")
    public ResponseEntity<LockDto> unlockLock(@PathVariable UUID lockId, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        LockDto lockDto = lockService.unlockLock(lockId, userId);
        return ResponseEntity.ok(lockDto);
    }



}
