package com.example.question_bank.form;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TestedForm {
    private String uuid;
    private String title;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    private int correctAnswer;
    private int answer;

    @Builder
    public TestedForm(String uuid, String title, String choice1, String choice2, String choice3, String choice4, int correctAnswer, int answer) {
        this.uuid = uuid;
        this.title = title;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correctAnswer = correctAnswer;
        this.answer = answer;
    }
}
