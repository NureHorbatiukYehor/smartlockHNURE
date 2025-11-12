package com.example.smartlock.controller;

import com.example.smartlock.dto.accesskey.AccessKeyDto;
import com.example.smartlock.dto.accesskey.CreateKeyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/key-access")
public class AccessKeyController {

    @PostMapping
    public ResponseEntity<AccessKeyDto> createAccessKeey(@RequestBody CreateKeyRequest createKeyRequest, Authentication authentication) {
        return null;
    }

    @GetMapping
    public ResponseEntity<AccessKeyDto> getAllKeysOnUser(Authentication authentication) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessKeyDto> getKeyById(@PathVariable UUID id, Authentication authentication) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccessKeyDto> deleteKeyById(@PathVariable UUID id, Authentication authentication) {
        return null;
    }
}
