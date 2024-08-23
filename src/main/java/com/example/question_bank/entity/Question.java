package com.example.question_bank.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="uuid", length = 40)
    private String uuid;

    private String title;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    @Column(name="correct_answer")
    private int correct_answer;


    }
