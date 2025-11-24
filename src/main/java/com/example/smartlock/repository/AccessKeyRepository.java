package com.example.smartlock.repository;

import com.example.smartlock.entity.AccessKey;
import com.example.smartlock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccessKeyRepository extends JpaRepository<AccessKey, UUID> {
    List<AccessKey> findAllByUser(User user);
}
