package com.yashank.question_service.Controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yashank.question_service.Modal.QuestionWapper;
import com.yashank.question_service.Modal.Questions;
import com.yashank.question_service.Modal.Response;
import com.yashank.question_service.Service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {
    
    private final QuestionService questionService;
    private final Environment  env;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Questions>> allQuestions()
    {
        return questionService.allQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsBasedOnCategory(@PathVariable String category)
    {
        return questionService.findByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<Questions> addQuestion(@RequestBody Questions questions)
    {
        return questionService.addQuestion(questions);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Questions> updateQuestion(@RequestBody Questions questions,@PathVariable int id)
    {
        return questionService.updateQuestion(questions,id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id)
    {
        return questionService.deleteQuestion(id);
    }

    //GenerateQuizQuestions
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String categoryName,@RequestParam int numQuestions)
    {
        return questionService.generateQuestionsforQuiz(categoryName,numQuestions);
    }

    //GetQuestionsforQuiz
    @PostMapping("/getquestions")
    public ResponseEntity<List<QuestionWapper>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds)
    {
        System.out.println(env.getProperty("local.server.port"));
        return questionService.getQuestionforQuiz(questionIds); 
    }

    @PostMapping("/getscore")
    public ResponseEntity<Integer> generateScore(@RequestBody List<Response> responses)
    {
        return questionService.generateScore(responses);
    }
}