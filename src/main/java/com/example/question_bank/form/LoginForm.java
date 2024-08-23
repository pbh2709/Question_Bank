package com.example.question_bank.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginForm {


    private Integer originalId;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{4,16}", message = "비밀번호는 4~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요. 아이디를 변경할 경우 기존 비밀번호를 다시 입력하세요.")
    private String password;

//    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{4,16}", message = "비밀번호는 4~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String passwordConfirm;


    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    }

