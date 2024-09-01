package com.example.question_bank.form;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@Data
public class ScoreDto {


    private  String name;

    private int count;


    @Builder
    public ScoreDto( String name,int count, int my_answer) {
        this.name = name;
        this.count = count;

    }
}
