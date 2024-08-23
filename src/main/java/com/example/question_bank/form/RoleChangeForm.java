package com.example.question_bank.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class RoleChangeForm {
    private int originalId;
    private String username;
    private String roles;
    private List<String> currentRoles;
    private Date createdAt;



    @Range(min = 1234,max = 1234,message =  "패스 워드 오류")
    private String password;

    private List<String> checkedRoles;
}

