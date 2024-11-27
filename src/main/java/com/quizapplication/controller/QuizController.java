package com.quizapplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizapplication.DTO.QuizAccessRequest;
import com.quizapplication.DTO.QuizDTO;
import com.quizapplication.DTO.QuizSubmissionDto;
import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.QuizSubmissionRequest;
import com.quizapplication.entity.User;
import com.quizapplication.entity.UserQuiz;
import com.quizapplication.repository.UserAnswersRepository;
import com.quizapplication.service.QuizService;
import com.quizapplication.service.UserAnswersService;
import com.quizapplication.service.UserQuizService;
import com.quizapplication.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/quizzes")
@CrossOrigin(origins = { "http://localhost:3000","https://quizapplicationbackend-production.up.railway.app"})
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

        Quiz createdQuiz = quizService.createQuiz(quizDTO.getSubject(), quizDTO.getStartDateTime(),quizDTO.getEndDateTime());
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
    @Autowired
    private UserService userService;
    
  

    @PostMapping("/submit")
    public ResponseEntity<String> submitQuiz(@RequestBody QuizSubmissionRequest request) {
        Long userId = request.getUserId();
        Long quizId = request.getQuizId();
        
        // Fetch the existing UserQuiz record
        UserQuiz userQuiz = userQuizService.findByUserIdAndQuizId(userId, quizId);
        
        // Ensure this method exists to retrieve the record
        if (userQuiz == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Quiz not found");
        }

        // Check if the quiz has already been submitted
        if (userQuiz.isHasSubmitted()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Quiz has already been submitted");
        }

        // Save the user's answers only if the quiz has not been submitted
        userAnswersService.saveAnswers(quizId, userId, request.getAnswers());
        
        // Mark the quiz as submitted
        userQuiz.setHasSubmitted(true);
        
        // Save the updated UserQuiz record
        userQuizService.updateUserQuiz(userQuiz); // Ensure this method updates the record properly

        return ResponseEntity.ok("Quiz submitted successfully");
    }

    
    
   
    @Autowired
    private UserQuizService userQuizService;
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
