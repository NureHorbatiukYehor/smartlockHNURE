package com.example.smartlock.service;

import com.example.smartlock.dto.LockDto;
import com.example.smartlock.entity.Lock;
import com.example.smartlock.repository.LockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LockService {
    LockRepository lockRepository;
    LockAccessService lockAccessService;

    public LockService(LockRepository lockRepository, LockAccessService lockAccessService) {
        this.lockRepository = lockRepository;
        this.lockAccessService = lockAccessService;
    }

    public List<LockDto> getAllLocksByUserId(UUID userId){
        List<UUID> lockIds = lockAccessService.getLockIdsByUserId(userId);
        List<Lock> locks = new ArrayList<>();
        List<LockDto> lockDtos = new ArrayList<>();

        for (UUID lockId : lockIds) {
            locks.add(lockRepository.findById(lockId).get());
        }
        for (Lock lock : locks) {
            lockDtos.add(new LockDto(
                    lock.getLockId(),
                    lock.getName(),
                    lock.getStatus(),
                    lock.getLastHeartbeatAt(),
                    lock.isLocked()
            ));
        }

        return lockDtos;
    }


}
