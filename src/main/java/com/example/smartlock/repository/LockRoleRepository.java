package com.example.smartlock.repository;

import com.example.smartlock.entity.Lock;
import com.example.smartlock.entity.LockRole;
import com.example.smartlock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LockRoleRepository extends JpaRepository <LockRole, UUID>{
    List<Lock> findAllLockByUser(User user);
    public void deleteByUserAndLock(User user, Lock lock);
}
