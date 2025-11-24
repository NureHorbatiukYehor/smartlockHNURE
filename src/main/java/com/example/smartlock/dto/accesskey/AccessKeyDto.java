package com.example.smartlock.dto.accesskey;

import java.time.OffsetDateTime;
import java.util.UUID;

public class AccessKeyDto {
    private UUID accessKeyId;
    private UUID lockId;
    private String accessToken;
    private boolean isActive;
    private OffsetDateTime validFrom;
    private OffsetDateTime validUntil;


    public AccessKeyDto(UUID accessKeyId, UUID lockId, String accessToken, boolean isActive, OffsetDateTime validFrom, OffsetDateTime validUntil) {
        this.accessKeyId = accessKeyId;
        this.lockId = lockId;
        this.accessToken = accessToken;
        this.isActive = isActive;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public UUID getaccessKeyId() {
        return accessKeyId;
    }

    public void setaccessKeyId(UUID tokenId) {
        this.accessKeyId = tokenId;
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

    public OffsetDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public OffsetDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(OffsetDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
