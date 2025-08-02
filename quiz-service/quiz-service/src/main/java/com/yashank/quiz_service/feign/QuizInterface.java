package com.yashank.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yashank.quiz_service.Modal.QuestionWapper;
import com.yashank.quiz_service.Modal.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    //GenerateQuizQuestions
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String categoryName,@RequestParam int numQuestions);

    //GetQuestionsforQuiz
    @PostMapping("question/getquestions")
    public ResponseEntity<List<QuestionWapper>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds);

    @PostMapping("question/getscore")
    public ResponseEntity<Integer> generateScore(@RequestBody List<Response> responses);
}
