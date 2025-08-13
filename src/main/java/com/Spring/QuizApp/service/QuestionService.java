package com.Spring.QuizApp.service;
import com.Spring.QuizApp.model.Question;
import com.Spring.QuizApp.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo repo;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        repo.save(question);
        try {
            return new ResponseEntity<>("Saved!!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            if (repo.existsById(id)) {
                repo.deleteById(id);
                return new ResponseEntity<>("Deleted..", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question with ID " + id + " not found!", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error deleting question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateQuestion(int id, Question updatedQuestion) {
        try {
            if (repo.existsById(id)) {
                updatedQuestion.setId(id);
                repo.save(updatedQuestion);
                return new ResponseEntity<>("Updated Successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question with ID " + id + " not found!", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
