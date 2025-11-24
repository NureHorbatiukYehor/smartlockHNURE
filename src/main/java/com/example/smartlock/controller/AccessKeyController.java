package com.example.smartlock.controller;

import com.example.smartlock.dto.accesskey.AccessKeyDto;
import com.example.smartlock.dto.accesskey.CreateKeyRequest;
import com.example.smartlock.entity.CustomUserDetails;
import com.example.smartlock.service.AccessKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/key-access")
public class AccessKeyController {

    private final AccessKeyService accessKeyService;

    public AccessKeyController(AccessKeyService accessKeyService) {
        this.accessKeyService = accessKeyService;
    }

    @PostMapping
    public ResponseEntity<AccessKeyDto> createAccessKey(@RequestBody CreateKeyRequest createKeyRequest, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        AccessKeyDto accessKeyDto = accessKeyService.createAccessKey(createKeyRequest, userId);
        return ResponseEntity.ok(accessKeyDto);
    }

    @GetMapping
    public ResponseEntity<List<AccessKeyDto>> getAllKeysOnUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        List<AccessKeyDto> accessKeyDtos = accessKeyService.getAllKeysOnUser(userId);
        return ResponseEntity.ok(accessKeyDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessKeyDto> getKeyById(@PathVariable UUID id) {
        AccessKeyDto accessKeyDto = accessKeyService.getAccessKeyById(id);
        return ResponseEntity.ok(accessKeyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccessKeyDto> deleteKeyById(@PathVariable UUID id, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        accessKeyService.deleteAccessKeyById(id, userId);
        return ResponseEntity.ok(null);
    }
}
