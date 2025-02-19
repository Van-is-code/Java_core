package com.studentdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.studentdb.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}