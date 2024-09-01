package com.example.question_bank.form;

import com.example.question_bank.entity.Question;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.ErrorResponse;

@NoArgsConstructor
@Data

public class InputForm {


    private  String uuid;
    @NotBlank(message = "필수 입력입니다.")
    private  String title;
    @NotBlank(message = "필수 입력입니다.")
    private  String choice1;
    @NotBlank(message = "필수 입력입니다.")
    private  String choice2;
    @NotBlank(message = "필수 입력입니다.")
    private  String choice3;
    @NotBlank(message = "필수 입력입니다.")
    private  String choice4;

    @Range(min=1, max=4, message="1~4의 정수 입력")
    private int answer;
    private  int my_answer;


    @Builder
    public InputForm(String uuid, String title,String choice1,String choice2,String choice3,String choice4, int answer, int my_answer) {
        this.uuid = uuid;
        this.title = title;
        this.choice1 =choice1;
        this.choice2 =choice2;
        this.choice3 =choice3;
        this.choice4 =choice4;
        this.answer = answer;
        this.my_answer = my_answer;
    }
}


