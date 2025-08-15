package com.Spring.QuizApp.service;

import com.Spring.QuizApp.model.Question;
import com.Spring.QuizApp.model.QuestionWrapper;
import com.Spring.QuizApp.model.Quiz;
import com.Spring.QuizApp.repo.QuestionRepo;
import com.Spring.QuizApp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepo.findById(id); //Optional cause if there is no data with the specific id
        //But in quiz it will return the Question data, but we need the QuestionWrapper data, so we need to convert it
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();
        //Converting using for loop
        for(Question q : questionsFromDb) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUsers.add(qw);
        }

        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
    }
}
