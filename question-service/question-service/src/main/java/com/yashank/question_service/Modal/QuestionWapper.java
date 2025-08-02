package com.yashank.question_service.Modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWapper {
    
    private int id;
    private String questionTitle;
    private String option1;
    private String option2;
}
