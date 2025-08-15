package com.Spring.QuizApp.repo;

import com.Spring.QuizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
