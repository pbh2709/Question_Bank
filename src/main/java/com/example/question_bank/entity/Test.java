package com.example.question_bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="score")
public class Test {

    @Id
    private  String student;
    private int my_answer1;
    private int my_answer2;
    private int my_answer3;
    private int my_answer4;
    private int my_answer5;
    private int my_answer6;
    private int my_answer7;
    private int my_answer8;
    private int my_answer9;
    private int my_answer10;
    private int my_answer11;
    private int my_answer12;
    private int my_answer13;
    private int my_answer14;
    private int my_answer15;
    private int my_answer16;
    private int my_answer17;
    private int my_answer18;
    private int my_answer19;
    private int my_answer20;
}
