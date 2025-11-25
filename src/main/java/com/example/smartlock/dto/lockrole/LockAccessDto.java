package com.example.smartlock.dto.lockrole;

import com.example.smartlock.enums.LockRole;

import java.util.UUID;

public class LockAccessDto {
    private UUID lockID;
    private UUID userID;
    private LockRole lockRole;

    public LockAccessDto(UUID lockID, UUID userID, LockRole lockRole) {
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

    public LockRole getLockRole() {
        return lockRole;
    }

    public void setLockRole(LockRole lockRole) {
        this.lockRole = lockRole;
    }
}
