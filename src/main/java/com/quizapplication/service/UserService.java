package com.quizapplication.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizapplication.DTO.UserScoreDto;
import com.quizapplication.entity.AdminRequest;
import com.quizapplication.entity.RequestStatus;
import com.quizapplication.entity.Score;
import com.quizapplication.entity.User;
import com.quizapplication.repository.ScoreRepository;
import com.quizapplication.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AdminRequestService adminRequestService;


    public void registerUser(String username, String email, String password, String confirmPassword, boolean isAdmin) {
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        // Check if the email is already used
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(password);

        // Create the User entity
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setConfirmPassword(confirmPassword);
        user.setIsAdmin(false); // Admin status is set to false by default

        // Save the user to the database
        userRepository.save(user);

        // Handle Admin request
        if (isAdmin) {
            AdminRequest adminRequest = new AdminRequest(user, RequestStatus.PENDING);
            adminRequestService.save(adminRequest); // Save the request for admin approval
        }

        // Send registration confirmation email
        sendConfirmationEmail(email);
    }


    private void sendConfirmationEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vstechhorizonsoftware@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Registration Successful");
        message.setText("Thank you for registering! Your account has been created successfully.");
        mailSender.send(message);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email); // Fetch user from the database using email
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // Return user if password matches
        }
        return null; // Return null if authentication fails
    }
    
    public User updateUser(User user) {
        return userRepository.save(user);
    }


	public Optional<User> findById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}
	
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
//	@Autowired
//    private ScoreRepository userScoreRepository;
//
//    public List<UserScoreDto> getUserHistory(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        // Fetch user's quiz scores
//        List<Score> scores = userScoreRepository.findByUserId(userId);
//
//        return scores.stream().map(score -> new UserScoreDto(
//                user.getUsername(),
//                user.getEmail(),
//                score.getQuiz().getSubject(),
//                score.getScore()
//        )).collect(Collectors.toList());
//    }
}
