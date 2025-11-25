package com.example.smartlock.model.dto.lock;

import java.util.UUID;

public class EditLockRequest {
    private UUID lockId;
    private String name;

    public EditLockRequest(UUID lockID, String name) {
        this.lockId = lockID;
        this.name = name;
    }

    public UUID getLockId() {
        return lockId;
    }
    public void setLockId(UUID lockId) {
        this.lockId = lockId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
