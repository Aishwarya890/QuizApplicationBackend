package com.quizapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapplication.DTO.UserScoreDto;
import com.quizapplication.entity.Quiz;
import com.quizapplication.entity.Score;
import com.quizapplication.repository.ScoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }
    
    public List<Score> getUserScoresByQuizId(Long quizId) {
        return scoreRepository.findByQuizId(quizId);
    }
    
    
    public List<Score> getUserHistory(Long userId) {
        return scoreRepository.findByUserId(userId);
    }
    
    public List<UserScoreDto> getScoresBySubject(String subject) {
        List<Object[]> results = scoreRepository.findScoresBySubject(subject);

        return results.stream()
            .map(result -> new UserScoreDto(
                (Integer) result[0],    // score
                (String) result[1],     // username
                (String) result[2]      // email
            ))
            .collect(Collectors.toList());
    }
  

    

}
