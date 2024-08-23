package com.example.question_bank.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginChangeForm {


    private Integer originalId;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String username;


    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Builder
    public LoginChangeForm(int originalId, String username,String email) {
        this.originalId = originalId;
        this.username = username;
        this.email=email;
    }

    }

