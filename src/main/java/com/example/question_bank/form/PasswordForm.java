package com.example.question_bank.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordForm {


    private  int  originalId;

    @NotBlank(message = "비어 있습니다.")
    private String password;

    @Builder
    public PasswordForm(int originalId, String password) {
        this.originalId = originalId;
        this.password = password;
    }
}
