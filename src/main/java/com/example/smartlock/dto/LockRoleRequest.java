package com.example.smartlock.dto;

import com.example.smartlock.enums.LockRole;

import java.util.UUID;

public class LockRoleRequest {
    private UUID lockID;
    private UUID userID;
    private LockRole lockRole;
}
