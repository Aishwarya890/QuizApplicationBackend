package com.quizapplication.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserHistoryDto {
	private Long userId;
    private String username;
    private String email;
    private String subject;
    private int score;

    public UserHistoryDto(String username, String email, String subject, int score) {
        this.username = username;
        this.email = email;
        this.subject = subject;
        this.score = score;
    }

	public UserHistoryDto(Long userId, String username, String email, String subject, int score) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.subject = subject;
		this.score = score;
	}

    // Getters and Setters
}
