package com.dsm.up.domain.user.domain.repository;

import com.dsm.up.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
}
