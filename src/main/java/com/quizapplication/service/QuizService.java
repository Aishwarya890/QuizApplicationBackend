package com.quizapplication.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapplication.entity.Quiz;
import com.quizapplication.repository.QuizRepository;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public Quiz createQuiz(String subject, LocalDateTime deadline) {
        Quiz quiz = new Quiz();
        quiz.setSubject(subject);
        
        quiz.setDeadline(deadline);
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
    
    public List<Quiz> getScheduledQuizzes() {
        
        LocalDateTime currentDate = LocalDateTime.now();
		return quizRepository.findByDeadlineAfter(currentDate);
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
            existingQuiz.setDeadline(updatedQuiz.getDeadline());
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
    
    
}
