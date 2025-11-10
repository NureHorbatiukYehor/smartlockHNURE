package com.example.smartlock.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name="full_name", nullable = false)
    private String fullName;

    @Column(name="created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private Set<Lock> locks;

    @OneToMany(mappedBy = "createdByUser")
    private Set<AccessKey> accessKeys;

    @OneToMany(mappedBy = "actorUser")
    private Set<ActivityLog> activityLogs;

    @OneToMany(mappedBy = "user")
    private Set<LockAccess> lockAccesses;

    protected User() {}

    public User(UUID userId, String email, String passwordHash, String fullName, OffsetDateTime createdAt, Set<Lock> locks, Set<AccessKey> accessKeys, Set<ActivityLog> activityLogs, Set<LockAccess> lockAccesses) {
        this.userId = userId;
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.locks = locks;
        this.accessKeys = accessKeys;
        this.activityLogs = activityLogs;
        this.lockAccesses = lockAccesses;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Lock> getLocks() {
        return locks;
    }

    public void setLocks(Set<Lock> locks) {
        this.locks = locks;
    }

    public Set<AccessKey> getAccessKeys() {
        return accessKeys;
    }

    public void setAccessKeys(Set<AccessKey> accessKeys) {
        this.accessKeys = accessKeys;
    }

    public Set<ActivityLog> getActivityLogs() {
        return activityLogs;
    }

    public void setActivityLogs(Set<ActivityLog> activityLogs) {
        this.activityLogs = activityLogs;
    }

    public Set<LockAccess> getLockAccesses() {
        return lockAccesses;
    }

    public void setLockAccesses(Set<LockAccess> lockAccesses) {
        this.lockAccesses = lockAccesses;
    }
}
