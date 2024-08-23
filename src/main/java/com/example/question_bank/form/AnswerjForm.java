package com.example.question_bank.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnswerjForm {


    @NotBlank(message = "답을 입력 하세요.")
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private String answer_5;

}
