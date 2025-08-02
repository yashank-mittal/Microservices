package com.yashank.quiz_service.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashank.quiz_service.Modal.QuestionWapper;
import com.yashank.quiz_service.Modal.Response;
import com.yashank.quiz_service.Modal.createQuiz;
import com.yashank.quiz_service.Service.QuizService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody createQuiz createQuiz )
    {
        return quizService.create(createQuiz.getCategoryName(),createQuiz.getNumQuestions(),createQuiz.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWapper>> getQuestionsFromQuiz(@PathVariable int id)
    {
        return quizService.getAllQuestionsFromQuiz(id);
    }
    
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getResult(@PathVariable int id,@RequestBody List<Response> responses)
    {
        return quizService.submit(id,responses);
    }
}
