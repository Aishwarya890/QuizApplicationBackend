package com.quizapplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.quizapplication.DTO.UserScoreDto;
import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.Score;
import com.quizapplication.entity.User;
import com.quizapplication.repository.QuizRepository;
import com.quizapplication.repository.ScoreRepository;
import com.quizapplication.repository.UserRepository;
import com.quizapplication.service.QuizService;
import com.quizapplication.service.ScoreService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://quizapplicationbackend-production.up.railway.app"})
@RequestMapping("/api/scores")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private UserRepository userRepository;

//    @PostMapping
//    public ResponseEntity<Score> saveScore(@RequestBody Score score) {
//        Score savedScore = scoreRepository.save(score);
//        return ResponseEntity.ok(savedScore);
//    }
    @PostMapping
    public ResponseEntity<?> saveScore(@RequestBody  UserScoreDto scoreDto) {
        try {
            // Retrieve the Quiz from the database using the quizId
            Quiz quiz = quizRepository.findById(scoreDto.getQuizId())
                .orElseThrow(() -> new Exception("Quiz not found with ID: " + scoreDto.getQuizId()));
            User user = userRepository.findById(scoreDto.getUserId())
                    .orElseThrow(() -> new Exception("user not found with ID: " + scoreDto.getUserId()));

            // Create a new Score entity and set its fields
            Score score = new Score();
            score.setUser(user);
            score.setScore(scoreDto.getScore());
            score.setQuiz(quiz); // Set the Quiz entity here

            // Save the Score entity
            scoreRepository.save(score);

            return ResponseEntity.ok("Score saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving score: " + e.getMessage());
        }
    }

  

    

}

