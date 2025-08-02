package com.yashank.quiz_service.Modal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWapper {
    
    private int id;
    private String questionTitle;
    private String option1;
    private String option2;
}
