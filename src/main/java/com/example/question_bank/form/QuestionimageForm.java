package com.example.question_bank.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@Data

public class QuestionimageForm {


    private  String uuid;
    @NotBlank(message = "필수 입력입니다.")
    private  String title;
    private byte[] image;
    @NotBlank(message = "필수 입력입니다.")
    private  String choice1;
    @NotBlank(message = "필수 입력입니다.")
    private  String choice2;
    @NotBlank(message = "필수 입력입니다.")
    private  String choice3;
    @NotBlank(message = "필수 입력입니다.")
    private  String choice4;

    @Range(min=1, max=4, message="1~4의 정수 입력")
    private int correct_answer;
    private  int my_answer;

 @Builder
    public QuestionimageForm(String uuid, String title, byte[] image, String choice1, String choice2, String choice3, String choice4, int correct_answer, int my_answer) {
        this.uuid = uuid;
        this.title = title;
        this.image = image;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correct_answer = correct_answer;
        this.my_answer = my_answer;
    }
}


