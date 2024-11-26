package com.quizapplication.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    // You can define custom query methods here if needed
	List<Score> findByUserId(Long userId);
	 
	 List<Score> findByQuizId(Long quizId);
	 
	 @Query("SELECT s.score, u.username, u.email FROM Score s " +
	           "JOIN s.user u " +
	           "JOIN s.quiz q " +
	           "WHERE q.subject = :subject")
	    List<Object[]> findScoresBySubject(String subject);
	 
}

