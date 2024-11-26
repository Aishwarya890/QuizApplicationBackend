package com.quizapplication.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private String subject;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    
	
    
    
}
