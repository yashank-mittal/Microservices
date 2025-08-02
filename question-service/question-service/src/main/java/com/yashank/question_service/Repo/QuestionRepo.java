package com.yashank.question_service.Repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yashank.question_service.Modal.Questions;




@Repository
public interface QuestionRepo extends JpaRepository<Questions,Integer> {
    List<Questions> findByCategory(String category);

    @Query(value = "SELECT * FROM questions WHERE category = :category ORDER BY RAND()", nativeQuery = true)
    List<Integer> findRandomQuestionsbyCategory(String category,Pageable pageable);
}
