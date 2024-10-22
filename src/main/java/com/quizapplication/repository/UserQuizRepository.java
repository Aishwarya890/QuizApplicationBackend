package com.quizapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.User;
import com.quizapplication.entity.UserQuiz;

@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {

    // Find a UserQuiz entry by userId and quizId
    UserQuiz findByUserIdAndQuizId(Long userId, Long quizId);
    UserQuiz findByUserAndQuiz(Optional<User> user, Optional<Quiz> quiz);
}

