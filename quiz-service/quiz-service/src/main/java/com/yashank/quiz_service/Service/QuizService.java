package com.yashank.quiz_service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yashank.quiz_service.Exception.ResourceNotFoundException;
import com.yashank.quiz_service.Modal.QuestionWapper;
import com.yashank.quiz_service.Modal.Quiz;
import com.yashank.quiz_service.Modal.Response;
import com.yashank.quiz_service.Repo.QuizRepo;
import com.yashank.quiz_service.feign.QuizInterface;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> create(String category, int numQ, String title) {
        
        ResponseEntity<List<Integer>> getQuestions = quizInterface.generateQuestionsForQuiz(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionids(getQuestions.getBody());
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWapper>> getAllQuestionsFromQuiz(int id) 
    {
       Quiz quiz=quizRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("This quiz is not Found "+id));
       List<Integer> questionids = quiz.getQuestionids();
        ResponseEntity<List<QuestionWapper>> questionFromQuestionService = quizInterface.getQuestionsForQuiz(questionids);
       return questionFromQuestionService;
    }

    public ResponseEntity<Integer> submit(int id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.generateScore(responses);
        return score;

    }
    
}
