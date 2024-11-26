package com.quizapplication.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.Score;
import com.quizapplication.entity.User;
import com.quizapplication.repository.QuizRepository;
import com.quizapplication.repository.ScoreRepository;
import com.quizapplication.repository.UserRepository;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public Quiz createQuiz(String subject, LocalDateTime startDateTime,LocalDateTime endDateTime) {
        Quiz quiz = new Quiz();
        quiz.setSubject(subject);
        
        quiz.setStartDateTime(startDateTime);
        quiz.setEndDateTime(endDateTime);
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
    
   
    
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> findById(Long quizId) {
        return quizRepository.findById(quizId);
    }
   
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }
    
    public Quiz getQuizById(Long id) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        return optionalQuiz.orElse(null); // Return the quiz if found, otherwise return null
    }
    
    public Quiz updateQuiz(Long id, Quiz updatedQuiz) throws Exception {
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if (optionalQuiz.isPresent()) {
            Quiz existingQuiz = optionalQuiz.get();
            existingQuiz.setSubject(updatedQuiz.getSubject());
            existingQuiz.setStartDateTime(updatedQuiz.getStartDateTime());
            existingQuiz.setEndDateTime(updatedQuiz.getEndDateTime());
            existingQuiz.setAccessCode(updatedQuiz.getAccessCode());
            existingQuiz.setQuestions(updatedQuiz.getQuestions());
            existingQuiz.setOptions(updatedQuiz.getOptions());
            existingQuiz.setCorrectAnswers(updatedQuiz.getCorrectAnswers());
            return quizRepository.save(existingQuiz);
        } else {
            throw new Exception("Quiz not found with id " + id);
        }
    }
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
    
    @Autowired
    private ScoreRepository userScoreRepository; // Inject your repository here


    
//    @Autowired
//    private UserRepository userRepository;
//    public void saveUserScore(Long userId, Long quizId, int score) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        Quiz quiz = quizRepository.findById(quizId)
//                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));
//
//        Score userScore = new Score();
//        userScore.setUser(user);
//        userScore.setQuiz(quiz);
//        userScore.setScore(score);
//
//        userScoreRepository.save(userScore);
//    }
    
}
