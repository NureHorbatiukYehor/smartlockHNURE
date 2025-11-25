package com.example.smartlock.dto.lockrole;

import com.example.smartlock.enums.UserRole;

import java.util.UUID;

public class LockRoleDto {
    private UUID lockID;
    private UUID userID;
    private UserRole lockRole;

    public LockRoleDto(UUID lockID, UUID userID, UserRole lockRole) {
        this.lockID = lockID;
        this.userID = userID;
        this.lockRole = lockRole;
    }

    public UUID getLockID() {
        return lockID;
    }

    public void setLockID(UUID lockID) {
        this.lockID = lockID;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public UserRole getLockRole() {
        return lockRole;
    }

    public void setLockRole(UserRole lockRole) {
        this.lockRole = lockRole;
    }
}
