package com.example.smartlock.dto.accesskey;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CreateKeyRequest {
    private UUID lockId;
    private OffsetDateTime validFrom;
    private OffsetDateTime validUntil;

    protected CreateKeyRequest() {}


    public UUID getLockId() {
        return lockId;
    }

    public void setLockId(UUID lockId) {
        this.lockId = lockId;
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
