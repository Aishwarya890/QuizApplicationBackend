package com.quizapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizAccessRequest {
    private Long quizId;
    private String accessCode;
    private Long userId;

    // Getters and setters
}
