package com.yashank.question_service.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yashank.question_service.Exception.ResourceNotFoundException;
import com.yashank.question_service.Modal.QuestionWapper;
import com.yashank.question_service.Modal.Questions;
import com.yashank.question_service.Modal.Response;
import com.yashank.question_service.Repo.QuestionRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepo questionRepo;

    public ResponseEntity<List<Questions>> allQuestions()
    {
        List<Questions> allQuestions = questionRepo.findAll();
        return ResponseEntity.ok(allQuestions);
    }

    public ResponseEntity<List<Questions>> findByCategory(String category) {
        List<Questions> questionByCategory = questionRepo.findByCategory(category);
        return ResponseEntity.ok(questionByCategory);
    }

    public ResponseEntity<Questions> addQuestion(Questions questions) {
        Questions addQuestions=questionRepo.save(questions);
        return ResponseEntity.ok(addQuestions);
    }

    public ResponseEntity<Questions> updateQuestion(Questions questions, int id) {
        Questions getQuestion = questionRepo.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));
    
        if (questions.getCategory() != null) {
            getQuestion.setCategory(questions.getCategory());
        }
        if (questions.getDifficultyLevel() != null) {
            getQuestion.setDifficultyLevel(questions.getDifficultyLevel());
        }
        if (questions.getOption1() != null) {
            getQuestion.setOption1(questions.getOption1());
        }
        if (questions.getOption2() != null) {
            getQuestion.setOption2(questions.getOption2());
        }
        if (questions.getQuestionTitle() != null) {
            getQuestion.setQuestionTitle(questions.getQuestionTitle());
        }
        if (questions.getRightAnswer() != null) {
            getQuestion.setRightAnswer(questions.getRightAnswer());
        }
    
        return ResponseEntity.ok(questionRepo.save(getQuestion));
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        questionRepo.deleteById(id);
        return ResponseEntity.ok("Deleted SuccessFully +"+id);
    }

    public ResponseEntity<List<Integer>> generateQuestionsforQuiz(String categoryName, int numQuestions) {
        List<Integer> noOfQuestions = questionRepo.findRandomQuestionsbyCategory(categoryName, PageRequest.of(0, numQuestions));
        return ResponseEntity.ok(noOfQuestions);
    }

    public ResponseEntity<List<QuestionWapper>> getQuestionforQuiz(List<Integer> questionIds) {
        // Validate input
        if (questionIds == null || questionIds.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<Questions> questions = questionRepo.findAllById(questionIds);
        
        // Check if all requested questions were found
        if (questions.size() != questionIds.size()) {
            // Log or handle missing questions
            List<Integer> foundIds = questions.stream()
                .map(Questions::getId)
                .toList();
            List<Integer> missingIds = questionIds.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();
            // You might want to log these missing IDs or handle them differently
        }

        // Use stream for more efficient mapping
        List<QuestionWapper> wrapper = questions.stream()
            .map(q -> {
                QuestionWapper qw = new QuestionWapper();
                qw.setId(q.getId());
                qw.setOption1(q.getOption1());
                qw.setOption2(q.getOption2());
                qw.setQuestionTitle(q.getQuestionTitle());
                return qw;
            })
            .toList();

        return ResponseEntity.ok(wrapper);
    }

    public ResponseEntity<Integer> generateScore(List<Response> responses) {
        int right = 0;
        for(Response response : responses)
        {
            Questions q = questionRepo.findById(response.getId()).orElseThrow(()->new ResourceNotFoundException("Question Not found :"+response.getId()));
            if(response.getResponse().equals(q.getRightAnswer())) right++;
        }
        return ResponseEntity.ok(right);
    }
}
