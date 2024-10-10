package com.quizapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LoginResponse {
    private String message;
    private boolean isAdmin;
    private boolean hasPendingRequests;
    private Long userId;
	public LoginResponse(boolean hasPendingRequests, Long userId , boolean isAdmin , String message ) {
		super();
		this.message =message ;
		this.isAdmin =isAdmin ;
		this.hasPendingRequests = hasPendingRequests;
		this.userId = userId;
	}

    
   
    
}