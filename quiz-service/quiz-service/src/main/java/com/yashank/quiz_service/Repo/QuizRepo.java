package com.yashank.quiz_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yashank.quiz_service.Modal.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {

    
    
}
