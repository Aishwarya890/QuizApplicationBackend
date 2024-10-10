package com.quizapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

	boolean existsByEmail(String email);
}
