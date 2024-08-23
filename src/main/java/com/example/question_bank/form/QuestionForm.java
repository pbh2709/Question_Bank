package com.example.question_bank.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class QuestionForm {


    private  String uuid;

    @NotBlank(message = "필수 입력입니다.")
    private  String title;



    @NotBlank(message = "필수 입력입니다.")
    private  String correct_answer;
    private  int my_answer;

    @Builder
    public QuestionForm(String uuid, String title, String correct_answer, int my_answer) {
        this.uuid = uuid;
        this.title = title;
        this.correct_answer = correct_answer;
        this.my_answer = my_answer;
    }
}




