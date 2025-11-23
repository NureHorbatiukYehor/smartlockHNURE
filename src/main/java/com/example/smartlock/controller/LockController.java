package com.example.smartlock.controller;

import com.example.smartlock.dto.CreateLockRequest;
import com.example.smartlock.dto.EditLockRequest;
import com.example.smartlock.dto.LockDto;
import com.example.smartlock.entity.CustomUserDetails;
import com.example.smartlock.entity.Lock;
import com.example.smartlock.service.LockService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/locks")
public class LockController {
    private final LockService lockService;

    public LockController(LockService lockService) {
        this.lockService = lockService;
    }

    @GetMapping
    public ResponseEntity<List<LockDto>> getAllLocksByUserId(Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        List<LockDto> lockDtos = lockService.getAllLocksByUserId(userId);

        return ResponseEntity.ok(lockDtos);
    }

    @PostMapping
    public ResponseEntity<LockDto> createLock(@RequestBody CreateLockRequest createLockRequest, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();
        LockDto lockDto = lockService.createLock(createLockRequest, userId);
        return ResponseEntity.ok(lockDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LockDto> deleteLock(@PathVariable UUID id, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        lockService.deleteLock(id, userId);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LockDto> editLock(@RequestBody EditLockRequest request, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        LockDto lockDto = lockService.editLock(request, userId);
        return ResponseEntity.ok(lockDto);
    }

    @PutMapping("/{id}/lock")
    public ResponseEntity<LockDto> lockLock(@PathVariable UUID id, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        LockDto lockDto = lockService.lockLock(id, userId);
        return ResponseEntity.ok(lockDto);
    }

    @PutMapping("/{id}/unlock")
    public ResponseEntity<LockDto> unlockLock(@PathVariable UUID id, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        LockDto lockDto = lockService.unlockLock(id, userId);
        return ResponseEntity.ok(lockDto);
    }



}
