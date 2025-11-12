package com.example.smartlock.controller;

import com.example.smartlock.dto.CreateLockRequest;
import com.example.smartlock.dto.LockDto;
import com.example.smartlock.entity.Lock;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/locks")
public class LockController {

    @GetMapping
    public ResponseEntity<Set<LockDto>> getAllLocksFromUser(Authentication authentication){
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<LockDto> createNewLock(@RequestBody CreateLockRequest createLockRequest, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LockDto> getLockById(@PathVariable UUID id, Authentication authentication){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LockDto> deleteLock(@PathVariable UUID id, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LockDto> editLock(@RequestBody LockDto lockDto, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}/lock")
    public ResponseEntity<LockDto> lockLock(@PathVariable UUID id, Authentication authentication) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}/open")
    public ResponseEntity<LockDto> openLock(@PathVariable UUID id, Authentication authentication) {
        return ResponseEntity.ok(null);
    }



}
