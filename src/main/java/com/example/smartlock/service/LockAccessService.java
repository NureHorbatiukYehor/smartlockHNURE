package com.example.smartlock.service;

import com.example.smartlock.repository.LockAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LockAccessService {
    private final LockAccessRepository lockAccessRepository;

    @Autowired
    public LockAccessService(LockAccessRepository lockAccessRepository) {
        this.lockAccessRepository = lockAccessRepository;
    }

    public List<UUID> getLockIdsByUserId(UUID userId){
        return lockAccessRepository.findAllLockIdByUserId(userId);
    }

}
