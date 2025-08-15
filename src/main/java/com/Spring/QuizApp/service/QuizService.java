package com.Spring.QuizApp.service;

import com.Spring.QuizApp.model.Question;
import com.Spring.QuizApp.model.Quiz;
import com.Spring.QuizApp.repo.QuestionRepo;
import com.Spring.QuizApp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionRepo repo;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = repo.findRandomQuestionsByCategory(category, numQ);
        //Creating new quiz
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        //Saving the quiz in quiz repo
        quizRepo.save(quiz);

        return new ResponseEntity<>("Success!!", HttpStatus.CREATED);
    }
}
