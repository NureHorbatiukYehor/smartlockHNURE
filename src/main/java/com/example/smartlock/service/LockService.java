package com.example.smartlock.service;

import com.example.smartlock.exceptions.exception.LockNotFoundException;
import com.example.smartlock.model.dto.lock.CreateLockRequest;
import com.example.smartlock.model.dto.lock.EditLockRequest;
import com.example.smartlock.model.dto.lock.LockDto;
import com.example.smartlock.model.entity.Lock;
import com.example.smartlock.model.enums.LockStatus;
import com.example.smartlock.repository.LockRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class LockService {
    LockRepository lockRepository;
    UserService userService;

    public LockService(LockRepository lockRepository, UserService userService) {
        this.lockRepository = lockRepository;
        this.userService = userService;
    }

    public LockDto fromLockToDto(Lock lock) {
        return new LockDto(
                lock.getLockId(),
                lock.getName(),
                lock.getStatus(),
                lock.getLastHeartbeatAt(),
                lock.isLocked()
        );
    };

    public Lock getLockById(UUID lockId) {
        return lockRepository.findById(lockId)
                .orElseThrow(()-> new LockNotFoundException("No lock with such id"));
    }

    public LockDto createLock(CreateLockRequest request, UUID userId){
        Lock lock = new Lock (
                userService.getUserById(userId),
                request.getName(),
                request.getSerialNumber(),
                request.getTimezone(),
                LockStatus.ONLINE,
                false,
                OffsetDateTime.now(),
                OffsetDateTime.now()
        );

        return fromLockToDto(
                lockRepository.save(lock)
        );

    }

    public void deleteLock(UUID id, UUID userId){
        lockRepository.deleteById(id);
    }

    public LockDto editLock(EditLockRequest request, UUID userId){
        Lock lock = lockRepository.findById(request.getLockId()).get();
        lock.setName(request.getName());

        return fromLockToDto(
                lockRepository.save(lock)
        );
    }


    public LockDto lockLock(UUID lockId, UUID userId) {
        //TODO IoT client
        Lock lock = lockRepository.findById(lockId).get();
        lock.setLocked(true);
        lock.setLastHeartbeatAt(OffsetDateTime.now());
        return fromLockToDto(
                lockRepository.save(lock)
        );
    }

    public LockDto unlockLock(UUID lockId, UUID userId) {
        //TODO IoT client
        Lock lock = lockRepository.findById(lockId).get();
        lock.setLocked(false);
        lock.setLastHeartbeatAt(OffsetDateTime.now());
        return fromLockToDto(
                lockRepository.save(lock)
        );
    }

}
