package com.dsm.up.domain.user.domain.repository;

import com.dsm.up.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
