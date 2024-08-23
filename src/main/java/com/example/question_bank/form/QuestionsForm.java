package com.example.question_bank.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class QuestionsForm {
    private  String uuid;

    @NotBlank(message = "필수 입력 입니다.")
    private  String title;

    private  String my_answer;


    @Builder
    public QuestionsForm(String uuid, String title,String my_answer) {
        this.uuid = uuid;
        this.title = title;
        this.my_answer=my_answer;
    }
}
