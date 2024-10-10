package com.quizapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapplication.DTO.QuizDTO;
import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.QuizSubmissionRequest;
import com.quizapplication.service.QuizService;
import com.quizapplication.service.UserAnswersService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/quizzes")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizDTO, HttpSession session) {
        // Check if the logged-in user is an admin
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin == null || !isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Quiz createdQuiz = quizService.createQuiz(quizDTO.getSubject(), quizDTO.getDescription(), quizDTO.getDeadline());
        return ResponseEntity.ok(createdQuiz);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null || !isLoggedIn) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }
    
    @Autowired
    private UserAnswersService userAnswersService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitQuiz(@RequestBody QuizSubmissionRequest request) {
       
        Long userId = request.getUserId(); // Replace this with actual user ID logic

        userAnswersService.saveAnswers(request.getQuizId(), userId, request.getAnswers());
        return ResponseEntity.ok("Quiz submitted successfully");
    }
}
