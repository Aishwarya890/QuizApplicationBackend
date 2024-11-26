//package com.quizapplication.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.quizapplication.DTO.UserHistoryDto;
//import com.quizapplication.entity.Score;
//import com.quizapplication.entity.User;
//import com.quizapplication.repository.ScoreRepository;
//import com.quizapplication.repository.UserRepository;
//
//@Service
//public class UserHistoryService {
//    private final ScoreRepository userScoreRepository;
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserHistoryService(ScoreRepository userScoreRepository, UserRepository userRepository) {
//        this.userScoreRepository = userScoreRepository;
//        this.userRepository = userRepository;
//    }
//
//    public List<UserHistoryDto> getUserHistory(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        List<Score> userScores = userScoreRepository.findByUserId(userId);
//
//        return userScores.stream()
//                .map(score -> new UserHistoryDto(
//                        user.getUsername(),
//                        user.getEmail(),
//                        score.getQuiz().getSubject(),
//                        score.getScore()))
//                .collect(Collectors.toList());
//    }
//}
//
