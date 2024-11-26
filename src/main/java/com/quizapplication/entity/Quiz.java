//package com.quizapplication.entity;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//
//@NoArgsConstructor
//@AllArgsConstructor
//public class Quiz {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String subject;
//    private LocalDateTime deadline;
//   
//    private String accessCode;
//    private List<String> correctAnswers;
//    @ElementCollection
//    private List<String> questions;
//
//    @ElementCollection
//    private List<List<String>> options;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getSubject() {
//		return subject;
//	}
//
//	public void setSubject(String subject) {
//		this.subject = subject;
//	}
//
//	public LocalDateTime getDeadline() {
//		return deadline;
//	}
//
//	public void setDeadline(LocalDateTime deadline) {
//		this.deadline = deadline;
//	}
//
//	public String getAccessCode() {
//		return accessCode;
//	}
//
//	public void setAccessCode(String accessCode) {
//		this.accessCode = accessCode;
//	}
//
//	public List<String> getCorrectAnswers() {
//		return correctAnswers;
//	}
//
//	public void setCorrectAnswers(List<String> correctAnswers) {
//		this.correctAnswers = correctAnswers;
//	}
//
//	public List<String> getQuestions() {
//		return questions;
//	}
//
//	public void setQuestions(List<String> questions) {
//		this.questions = questions;
//	}
//
//	public List<List<String>> getOptions() {
//		return options;
//	}
//
//	public void setOptions(List<List<String>> options) {
//		this.options = options;
//	}
//
//
//    // Getters and Setters
//    

//}

package com.quizapplication.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String subject;
    
    private LocalDateTime startDateTime;  // New field for start date and time
    private LocalDateTime endDateTime;    // New field for end date and time
    
 
    private String accessCode;
    
    @ElementCollection
    private List<String> correctAnswers;
    
    @ElementCollection
    private List<String> questions;
    
    @ElementCollection
    private List<List<String>> options;

    // Getters and Setters for all fields, including new ones

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

  
    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<List<String>> getOptions() {
        return options;
    }

    public void setOptions(List<List<String>> options) {
        this.options = options;
    }
}
