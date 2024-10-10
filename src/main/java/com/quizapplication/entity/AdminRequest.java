package com.quizapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String email;
    private String password;  // Store hashed password

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @ManyToOne 
    @JoinColumn(name = "user_id")// Assuming each request is linked to one user
    private User user;  // Reference to the user who made the request

		public AdminRequest(User user, RequestStatus status) {
	        this.user = user; // Set the User entity directly
	        this.username = user.getUsername();
	        this.email = user.getEmail();
	        this.password = user.getPassword(); // Consider security implications here
	        this.status = status;
	    }
	

    // Getters, Setters, Constructors
	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}


