package com.example.smartlock.repository;

import com.example.smartlock.entity.Lock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LockRepository extends JpaRepository<Lock, UUID> {
}
