package com.example.smartlock.service;

import com.example.smartlock.dto.CreateLockRequest;
import com.example.smartlock.dto.EditLockRequest;
import com.example.smartlock.dto.LockDto;
import com.example.smartlock.entity.Lock;
import com.example.smartlock.enums.LockStatus;
import com.example.smartlock.repository.LockRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LockService {
    LockRepository lockRepository;
    LockAccessService lockAccessService;
    UserService userService;

    public LockService(LockRepository lockRepository, LockAccessService lockAccessService, UserService userService) {
        this.lockRepository = lockRepository;
        this.lockAccessService = lockAccessService;
        this.userService = userService;
    }

    private LockDto fromLockToDto(Lock lock) {
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
                .orElseThrow(()-> new RuntimeException()); //no lock with such id
    }

    public List<LockDto> getAllLocksByUserId(UUID userId){
        List<UUID> lockIds = lockAccessService.getLockIdsByUserId(userId);
        List<Lock> locks = new ArrayList<>();
        List<LockDto> lockDtos = new ArrayList<>();

        for (UUID lockId : lockIds) {
            locks.add(lockRepository.findById(lockId).get());
        }
        for (Lock lock : locks) {
            lockDtos.add(fromLockToDto(lock));
        }

        return lockDtos;
    }

    public LockDto createLock(CreateLockRequest request, UUID userId){
        Lock lock = new Lock (
                userService.getUserById(userId).get(),
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
        //TODO check permission == OWNER
        lockRepository.deleteById(id);
    }

    public LockDto editLock(EditLockRequest request, UUID userId){
        //TODO check permission == OWNER or ADMIN
        Lock lock = lockRepository.findById(request.getLockId()).get();
        lock.setName(request.getName());

        return fromLockToDto(
                lockRepository.save(lock)
        );
    }


    public LockDto lockLock(UUID lockId, UUID userId) {
        //TODO check permissions
        //TODO IoT client
        Lock lock = lockRepository.findById(lockId).get();
        lock.setLocked(true);
        lock.setLastHeartbeatAt(OffsetDateTime.now());
        return fromLockToDto(
                lockRepository.save(lock)
        );
    }

    public LockDto unlockLock(UUID lockId, UUID userId) {
        //TODO check permissions
        //TODO IoT client
        Lock lock = lockRepository.findById(lockId).get();
        lock.setLocked(false);
        lock.setLastHeartbeatAt(OffsetDateTime.now());
        return fromLockToDto(
                lockRepository.save(lock)
        );
    }

}
