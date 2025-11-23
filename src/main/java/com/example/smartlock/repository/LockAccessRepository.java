package com.example.smartlock.repository;

import com.example.smartlock.entity.LockAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LockAccessRepository extends JpaRepository <LockAccess, UUID>{
    public List<UUID> findAllLockIdByUserId(UUID userId);
}
