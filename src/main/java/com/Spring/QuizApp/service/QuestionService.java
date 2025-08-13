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

    public List<Question> getQuestionsByCategory(String category) {
        return repo.findByCategory(category);
    }

    public String addQuestion(Question question) {
        repo.save(question);
        return "Saved!!";
    }

    public String deleteQuestion(int id) {
        repo.deleteById(id);
        return "Deleted..";
    }

    public String updateQuestion(int id, Question updatedQuestion) {
        if (repo.existsById(id)) {
            updatedQuestion.setId(id); // Ensure ID remains the same
            repo.save(updatedQuestion);
            return "Updated Successfully!";
        } else {
            return "Question with ID " + id + " not found!";
        }
    }
}
