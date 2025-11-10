package com.example.smartlock.repository;

import com.example.smartlock.entity.AccessKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccessKeyRepository extends JpaRepository<AccessKey, UUID> {
}
