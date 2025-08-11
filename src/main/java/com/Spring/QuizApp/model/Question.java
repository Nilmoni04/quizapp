package com.Spring.QuizApp.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "quiz_questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String difficulty_level;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String right_answer;
}
