package com.yashank.quiz_service.Modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class createQuiz {
    String categoryName;
    int numQuestions;
    String title;
}
