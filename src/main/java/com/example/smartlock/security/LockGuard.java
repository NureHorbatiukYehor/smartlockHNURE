package com.example.smartlock.security;

import com.example.smartlock.model.entity.CustomUserDetails;
import com.example.smartlock.repository.LockRepository;
import com.example.smartlock.repository.LockRoleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component("lockGuard")
public class LockGuard {
    private final LockRoleRepository lockRoleRepository;
    private final LockRepository lockRepository;

    public LockGuard(LockRoleRepository lockRoleRepository, LockRepository lockRepository) {
        this.lockRoleRepository = lockRoleRepository;
        this.lockRepository = lockRepository;
    }

    public boolean check(UUID lockId, String... allowedRoles) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID currentUserId = ((CustomUserDetails) auth.getPrincipal()).getId();

        List<String> allowedRoleList = Arrays.asList(allowedRoles);


        boolean debug = lockRoleRepository.existsByUserUserIdAndLockLockIdAndLockRoleIn(currentUserId, lockId, allowedRoleList);
        System.out.println("DEBUG: ID: " + currentUserId + " LOCK ID: " + lockId + " ALLOWED ROLES: " + allowedRoleList + " CHECK: " + debug);

        return debug;
    }

    public boolean checkLockSecret(UUID lockId, String secretKey) {
        return lockRepository.existsByLockIdAndSecretKey(lockId, secretKey);
    }
}
