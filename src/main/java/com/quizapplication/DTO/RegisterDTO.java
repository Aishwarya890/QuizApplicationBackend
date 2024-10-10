package com.quizapplication.DTO;

public class RegisterDTO {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean isAdmin;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public RegisterDTO(String username, String email, String password, String confirmPassword, boolean isAdmin) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.isAdmin = isAdmin;
	}
	public RegisterDTO() {
		super();
	}

    // Getters and Setters
    
}