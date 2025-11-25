package com.example.smartlock.service;

import com.example.smartlock.dto.accesskey.AccessKeyDto;
import com.example.smartlock.dto.lock.LockDto;
import com.example.smartlock.dto.lockrole.LockAccessDto;
import com.example.smartlock.entity.Lock;
import com.example.smartlock.entity.LockAccess;
import com.example.smartlock.entity.User;
import com.example.smartlock.enums.LockRole;
import com.example.smartlock.repository.LockAccessRepository;
import com.example.smartlock.repository.LockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LockAccessService {
    private final LockAccessRepository lockAccessRepository;
    private final LockService lockService;
    private final UserService userService;

    @Autowired
    public LockAccessService(LockAccessRepository lockAccessRepository, LockService lockService, UserService userService) {
        this.lockAccessRepository = lockAccessRepository;
        this.lockService = lockService;
        this.userService = userService;
    }

    public LockAccessDto fromLockAccessToDto(LockAccess lockAccess) {
        return new LockAccessDto(
                lockAccess.getLock().getLockId(),
                lockAccess.getUser().getUserId(),
                lockAccess.getLockRole()
        );
    }


    public List<LockDto> getAllLocksByUserId(UUID userId){
        List<Lock> locks = lockAccessRepository.findAllLockByUser(userService.getUserById(userId));
        List<LockDto> lockDtos = new ArrayList<>();

        for (Lock lock : locks) {
            lockDtos.add(lockService.fromLockToDto(lock));
        }

        return lockDtos;
    }

    public LockAccessDto addUserToLock(LockAccessDto lockAccessDto, UUID userId, UUID lockId, UUID actorUserId) {
        //TODO check permissions
        lockAccessRepository.save( new LockAccess(
                userService.getUserById(userId),
                lockService.getLockById(lockId),
                lockAccessDto.getLockRole(),
                OffsetDateTime.now()
                )
        );
        return lockAccessDto;
    }

    public void deleteUserFromLock(UUID userId, UUID lockId, UUID actorUserId) {
        //TODO check permissions
        Lock lock = lockService.getLockById(lockId);
        User user = userService.getUserById(userId);

        lockAccessRepository.deleteByUserAndLock(user, lock);
    }

    public LockAccessDto changeUserLockRole(UUID userId, UUID lockId, UUID actorUserId, LockRole lockRole) {
        //TODO check permissions
        Lock lock = lockService.getLockById(lockId);
        User user = userService.getUserById(userId);


        return fromLockAccessToDto(
                lockAccessRepository.save(new LockAccess(user, lock, lockRole, OffsetDateTime.now()))
        );
    }
}
