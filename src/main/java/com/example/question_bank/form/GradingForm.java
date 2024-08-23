package com.example.question_bank.form;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GradingForm {




    private String uuid3;



    @NotBlank(message = "입력")
    private String correctsAnswerScore;


    @Builder
    public GradingForm(String uuid3, String correctsAnswerScore) {
        this.uuid3= uuid3;
        this.correctsAnswerScore = correctsAnswerScore;

    }
}
