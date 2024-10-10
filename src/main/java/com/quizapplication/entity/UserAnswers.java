package com.quizapplication.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_answers")
public class UserAnswers {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
// @ManyToOne
// @JoinColumn(name = "user_id")
 private Long quizId;
 private Long userId; // You may want to track which user submitted the answers

 @ElementCollection
 private List<String> answers;

 
}
