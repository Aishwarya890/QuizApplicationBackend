package com.quizapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapplication.entity.UserAnswers;
import com.quizapplication.repository.UserAnswersRepository;

@Service
public class UserAnswersService {

 @Autowired
 private UserAnswersRepository userAnswersRepository;

 public UserAnswers saveAnswers(Long quizId, Long userId, List<String> answers) {
     UserAnswers userAnswers = new UserAnswers();
     userAnswers.setQuizId(quizId);
     userAnswers.setUserId(userId);
     userAnswers.setAnswers(answers);
     return userAnswersRepository.save(userAnswers);
 }
}
