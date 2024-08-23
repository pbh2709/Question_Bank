package com.example.question_bank.form;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NameForm {

    @NotBlank(message = "필수 입력")
    public String userName;

}
