package com.quizapplication.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapplication.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAll();
//    List<Quiz> findByDeadlineAfter(LocalDateTime currentDate);
}