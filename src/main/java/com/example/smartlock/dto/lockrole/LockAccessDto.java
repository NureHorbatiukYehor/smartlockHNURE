package com.example.smartlock.dto.lockrole;

import com.example.smartlock.enums.LockRole;

import java.util.UUID;

public class LockAccessDto {
    private UUID lockID;
    private UUID userID;
    private LockRole lockRole;
}
