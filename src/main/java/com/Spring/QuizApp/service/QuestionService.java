package com.Spring.QuizApp.service;
import com.Spring.QuizApp.model.Question;
import com.Spring.QuizApp.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo repo;
    public List<Question> getAllQuestions() {
        return repo.findAll();
    }
}
