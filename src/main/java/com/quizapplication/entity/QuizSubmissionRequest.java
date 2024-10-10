package com.quizapplication.entity;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizSubmissionRequest {
	private Long quizId;
    private List<String> answers;
    private Long userId;
    

}
