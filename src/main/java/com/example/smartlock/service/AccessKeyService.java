package com.example.smartlock.service;

import com.example.smartlock.dto.accesskey.AccessKeyDto;
import com.example.smartlock.dto.accesskey.CreateKeyRequest;
import com.example.smartlock.entity.AccessKey;
import com.example.smartlock.repository.AccessKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccessKeyService {
    LockService lockService;
    UserService userService;

    private final AccessKeyRepository accessKeyRepository;

    @Autowired
    public AccessKeyService(LockService lockService, UserService userService, AccessKeyRepository accessKeyRepository) {
        this.lockService = lockService;
        this.userService = userService;
        this.accessKeyRepository = accessKeyRepository;
    }

    private AccessKeyDto fromAccessKeyToDto(AccessKey accessKey) {
        return new AccessKeyDto(
                accessKey.getAccessKeyId(),
                accessKey.getLock().getLockId(),
                accessKey.getToken(),
                accessKey.isActive(),
                accessKey.getValidFrom(),
                accessKey.getValidUntil()
        );
    }

    public AccessKeyDto createAccessKey(CreateKeyRequest request, UUID userId) {
        String token = "token";
        AccessKey accessKey = new AccessKey(
                lockService.getLockById(request.getLockId()),
                userService.getUserById(userId),
                token,
                request.getValidFrom(),
                request.getValidUntil(),
                true,
                OffsetDateTime.now()
        );
        return fromAccessKeyToDto(accessKeyRepository.save(accessKey));
    }

    public List<AccessKeyDto> getAllKeysOnUser(UUID userId) {
        List<AccessKeyDto> accessKeyDtos = new ArrayList<>();
        List<AccessKey> accessKeys = accessKeyRepository.findAllByUser(userService.getUserById(userId));

        for (AccessKey accessKey : accessKeys) {
            accessKeyDtos.add(fromAccessKeyToDto(accessKey));
        }
        return accessKeyDtos;
    }

    public AccessKeyDto getAccessKeyById(UUID id) {
        return fromAccessKeyToDto(accessKeyRepository.findById(id).get());
    }

    public void deleteAccessKeyById(UUID id, UUID userId) {
        //TODO check permissions
        accessKeyRepository.deleteById(id);
    }
}
