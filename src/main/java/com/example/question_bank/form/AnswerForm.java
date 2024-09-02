package com.example.question_bank.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnswerForm {


    @NotBlank(message = "답을 입력 하세요.")
    private  int count;
    private int answer1;
    private int answer2;
    private int answer3;
    private int answer4;
    private int answer5;
    private int answer_6;
    private int answer_7;
    private int answer_8;
    private int answer_9;
    private int answer_10;
    private int answer_11;
    private int answer_12;
    private int answer_13;
    private int answer_14;
    private int answer_15;
    private int answer_16;
    private int answer_17;
    private int answer_18;
    private int answer_19;
    private int answer_20;
    private String answerj_1;
    private String answerj_2;
    private String answerj_3;
    private String answerj_4;
    private String answerj_5;
    private String answers;
    private String answers_2;
    private String answers_3;
    private String answers_4;
    private String answers_5;
    private int answerimage_1;
    private int answerimage_2;
    private int answerimage_3;
    private int answerimage_4;
    private int answerimage_5;

    @NotBlank(message = "필수 입력")
    private String name;
    private String uuid;


}
