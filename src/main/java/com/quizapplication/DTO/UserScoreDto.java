package com.quizapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserScoreDto {
    private String username;
    private String email;
   
    private Long userId;
    private Long quizId;
    private Integer score;
   

    public UserScoreDto(Integer score,String username, String email) {
    	 this.score = score;
    	this.username = username;
        this.email = email;
       
       
    }
	
	public UserScoreDto(Long userId, Long quizId, Integer score) {
		super();
		this.userId = userId;
		this.quizId = quizId;
		this.score = score;
	}

	
	
    // Getters and Setters
}

