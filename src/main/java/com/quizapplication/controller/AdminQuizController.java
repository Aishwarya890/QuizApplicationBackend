package com.quizapplication.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapplication.DTO.UserScoreDto;
import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.Score;
import com.quizapplication.entity.User;
import com.quizapplication.service.QuizService;
import com.quizapplication.service.ScoreService;
import com.quizapplication.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://quizapplicationbackend-production.up.railway.app"})
@RequestMapping("/api/admin/quizzes")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminQuizController {
    @Autowired
    private QuizService quizService;

    
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        try {
            Quiz createdQuiz = quizService.save(quiz);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz); // Return 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 in case of error
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        try {
            List<Quiz> quizzes = quizService.findAll();
            return ResponseEntity.ok(quizzes); // Return 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return 500 in case of error
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz != null) {
            return ResponseEntity.ok(quiz);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) throws Exception {
        Quiz quiz = quizService.updateQuiz(id, updatedQuiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ScoreService scoreService;
//    @GetMapping("/user-scores")
//    public List<Map<String, Object>> getUserScores() {
//        List<User> users = userService.getAllUsers();
//        List<Score> scores = scoreService.getAllScores();
//
//        // Create a list to hold user-score mappings
//        List<Map<String, Object>> userScores = new ArrayList<>();
//
//        for (User user : users) {
//            Map<String, Object> userScoreMap = new HashMap<>();
//            userScoreMap.put("name", user.getUsername());
//            userScoreMap.put("email", user.getEmail());
//            // Find the score for the current user
//            Score userScore = scores.stream()
//                .filter(score -> score.getUserId().equals(user.getId()))
//                .findFirst()
//                .orElse(null);
//            userScoreMap.put("score", userScore != null ? userScore.getScore() : 0); // Set score or 0 if not found
//            userScores.add(userScoreMap);
//        }
//        return userScores;
//    }
    @GetMapping("/user-scores")
    public List<Map<String, Object>> getUserScores() {
        List<User> users = userService.getAllUsers();
        List<Score> scores = scoreService.getAllScores();

        // Create a list to hold user-score mappings
        List<Map<String, Object>> userScores = new ArrayList<>();

        for (User user : users) {
            Map<String, Object> userScoreMap = new HashMap<>();
            userScoreMap.put("name", user.getUsername());
            userScoreMap.put("email", user.getEmail());

            // Ensure the user ID is not null
            if (user.getId() != null) {
                // Find the score for the current user, with null check
                Score userScore = scores.stream()
                    .filter(score -> score.getId() != null && score.getId().equals(user.getId()))
                    .findFirst()
                    .orElse(null);
                
                // Set score or 0 if not found
                userScoreMap.put("score", userScore != null ? userScore.getScore() : 0);
            } else {
                userScoreMap.put("score", 0); // Default score if user ID is null
            }

            userScores.add(userScoreMap);
        }
        return userScores;
    }

//     
//    @GetMapping("/user-scores/{subject}")
//    public ResponseEntity<List<UserScoreDto>> getUserScoresBySubject(@PathVariable String subject) {
//        List<UserScoreDTO> userScores = quizService.getUserScoresBySubject(subject);
//        return ResponseEntity.ok(userScores);
//    }
    
    @GetMapping("/user-scores/{subject}")
    public ResponseEntity<List<UserScoreDto>> getScoresBySubject(@PathVariable String subject) {
        List<UserScoreDto> scores = scoreService.getScoresBySubject(subject);
        return ResponseEntity.ok(scores);
    }
    
    
    
    
}

