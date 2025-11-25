package com.example.smartlock.service;

import com.example.smartlock.model.dto.lock.LockDto;
import com.example.smartlock.model.dto.lockrole.LockRoleDto;
import com.example.smartlock.model.entity.Lock;
import com.example.smartlock.model.entity.LockRole;
import com.example.smartlock.model.entity.User;
import com.example.smartlock.model.enums.UserRole;
import com.example.smartlock.repository.LockRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LockRoleService {
    private final LockRoleRepository lockRoleRepository;
    private final LockService lockService;
    private final UserService userService;

    @Autowired
    public LockRoleService(LockRoleRepository lockAccessRepository, LockService lockService, UserService userService) {
        this.lockRoleRepository = lockAccessRepository;
        this.lockService = lockService;
        this.userService = userService;
    }

    public LockRoleDto fromLockRoleToDto(LockRole lockAccess) {
        return new LockRoleDto(
                lockAccess.getLock().getLockId(),
                lockAccess.getUser().getUserId(),
                lockAccess.getLockRole()
        );
    }


    public List<LockDto> getAllLocksByUserId(UUID userId){
        List<Lock> locks = lockRoleRepository.findAllLockByUser(userService.getUserById(userId));
        List<LockDto> lockDtos = new ArrayList<>();

        for (Lock lock : locks) {
            lockDtos.add(lockService.fromLockToDto(lock));
        }

        return lockDtos;
    }

    public LockRoleDto addUserToLock(UserRole userRole, UUID userId, UUID lockId) {
        //TODO check permissions
        return fromLockRoleToDto(
                lockRoleRepository.save( new LockRole(
                                userService.getUserById(userId),
                                lockService.getLockById(lockId),
                                userRole,
                                OffsetDateTime.now()
                        )
                )
        );
    }

    public void deleteUserFromLock(UUID userId, UUID lockId) {
        //TODO check permissions
        Lock lock = lockService.getLockById(lockId);
        User user = userService.getUserById(userId);

        lockRoleRepository.deleteByUserAndLock(user, lock);
    }

    public LockRoleDto changeUserLockRole(UUID userId, UUID lockId, UserRole userRole) {
        //TODO check permissions
        Lock lock = lockService.getLockById(lockId);
        User user = userService.getUserById(userId);

        LockRole lockRole = lockRoleRepository.findByLockAndUser(lock, user);
        lockRole.setLockRole(userRole);

        return fromLockRoleToDto(
                lockRoleRepository.save(lockRole)
        );
    }
}
