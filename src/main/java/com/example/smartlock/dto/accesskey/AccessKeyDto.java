package com.example.smartlock.dto.accesskey;

import java.time.OffsetDateTime;
import java.util.UUID;

public class AccessKeyDto {
    private UUID tokenId;
    private UUID lockId;
    private String accessToken;
    private boolean isActive;


    protected AccessKeyDto() {}

    public UUID getTokenId() {
        return tokenId;
    }

    public void setTokenId(UUID tokenId) {
        this.tokenId = tokenId;
    }

    public UUID getLockId() {
        return lockId;
    }

    public void setLockId(UUID lockId) {
        this.lockId = lockId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
