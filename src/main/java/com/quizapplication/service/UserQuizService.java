package com.quizapplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.User;
import com.quizapplication.entity.UserQuiz;
import com.quizapplication.repository.QuizRepository;
import com.quizapplication.repository.UserQuizRepository;
import com.quizapplication.repository.UserRepository;


//@Service
//public class UserQuizService {
//
//    private final UserQuizRepository userQuizRepository;
//   
//    public UserQuizService(UserQuizRepository userQuizRepository) {
//        this.userQuizRepository = userQuizRepository;
//    }
//
//    // Method to find UserQuiz by user and quiz
//    public UserQuiz findByUserAndQuiz(Long userId, Long quizId) {
//        return userQuizRepository.findByUserIdAndQuizId(userId, quizId);
//    }
//
//    // Method to save or update a UserQuiz record
////    public UserQuiz save(UserQuiz userQuiz) {
////    	
////    	
////        return userQuizRepository.save(userQuiz);
////    }
//    
//    @Autowired
//    private QuizRepository quizRepository;
//
//    @Autowired
//    private UserRepository userRepository;
////    public UserQuiz save(Long userId, Long quizId) {
////        // Fetch the user and quiz based on the given IDs
////        User user = userRepository.findById(userId)
////            .orElseThrow(() -> new RuntimeException("User not found"));
////        
////        Quiz quiz = quizRepository.findById(quizId)
////            .orElseThrow(() -> new RuntimeException("Quiz not found"));
////
////        // Create a new UserQuiz object
////        UserQuiz userQuiz = new UserQuiz();
////        userQuiz.setUser(user);
////        userQuiz.setQuiz(quiz);
////        userQuiz.setHasSubmitted(false); // Set the initial submission status
////
////        // Save and return the UserQuiz record
////        return userQuizRepository.save(userQuiz);
////    }
//    
//  
//
//}
@Service
public class UserQuizService {

    private final UserQuizRepository userQuizRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserQuizService(UserQuizRepository userQuizRepository, QuizRepository quizRepository, UserRepository userRepository) {
        this.userQuizRepository = userQuizRepository;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    public UserQuiz save(Long userId, Long quizId) {
        // Check if the UserQuiz already exists
        UserQuiz existingUserQuiz = userQuizRepository.findByUserIdAndQuizId(userId, quizId);
        if (existingUserQuiz != null) {
            return existingUserQuiz; // Return the existing UserQuiz without throwing an error
        }

        // Fetch the user and quiz based on the given IDs
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Quiz quiz = quizRepository.findById(quizId)
            .orElseThrow(() -> new RuntimeException("Quiz not found"));

        // Create a new UserQuiz object
        UserQuiz userQuiz = new UserQuiz();
        userQuiz.setUser(user);
        userQuiz.setQuiz(quiz);
        userQuiz.setHasSubmitted(false); // Initial submission status

        // Save and return the UserQuiz record
        return userQuizRepository.save(userQuiz);
    }

       

    // Method to mark a quiz as submitted by the user
    public void submitQuiz(Long userId, Long quizId) {
        UserQuiz userQuiz = userQuizRepository.findByUserIdAndQuizId(userId, quizId);

        if (userQuiz == null) {
            throw new RuntimeException("User has not started this quiz.");
        }

        if (userQuiz.isHasSubmitted()) {
            throw new RuntimeException("You have already submitted this quiz.");
        }

        // Mark the quiz as submitted
        userQuiz.setHasSubmitted(true);
        userQuizRepository.save(userQuiz);
    }
    public UserQuiz findByUserIdAndQuizId(Long userId, Long quizId) {
    	System.out.println("Finding UserQuiz for UserId: " + userId + " and QuizId: " + quizId);
    	return userQuizRepository.findByUserIdAndQuizId(userId, quizId);
    }

    public void updateUserQuiz(UserQuiz userQuiz) {
        userQuizRepository.save(userQuiz); // Make sure the repository's save method is called to persist changes
    }
    

	

}

