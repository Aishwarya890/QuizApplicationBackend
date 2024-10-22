package com.quizapplication.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizapplication.DTO.QuizAccessRequest;
import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.UserQuiz;
import com.quizapplication.service.QuizService;
import com.quizapplication.service.UserQuizService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class UserQuizController {

    private final QuizService quizService;
    private final UserQuizService userQuizService;

    public UserQuizController(QuizService quizService, UserQuizService userQuizService) {
        this.quizService = quizService;
        this.userQuizService = userQuizService;
    }

    @PostMapping("/verify-quiz-access")
    public ResponseEntity<String> verifyQuizAccess(@RequestBody QuizAccessRequest request) {
        Long quizId = request.getQuizId();
        String accessCode = request.getAccessCode();
        Long userId = request.getUserId();

        Optional<Quiz> quizOptional = quizService.findById(quizId);
        
        if (!quizOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
        }

        Quiz quiz = quizOptional.get();

        // Trim both codes for comparison
        if (!quiz.getAccessCode().trim().equals(accessCode.trim())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid access code");
        }

        UserQuiz userQuiz = userQuizService.save(userId, quizId);
        if (userQuiz != null && userQuiz.isHasSubmitted()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You have already taken this quiz");
        }

        return ResponseEntity.ok("Access granted");
    }
    
    

}
