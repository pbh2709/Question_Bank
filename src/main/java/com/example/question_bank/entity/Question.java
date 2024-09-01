package com.example.question_bank.entity;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Question extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", length = 40)
    private String uuid;
    private String testuuid;
    private String title;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    @Column(name = "answer")
    private int answer;

    @Column(name = "my")
    private int my;


    @Builder
    public Question(String testuuid) {
        this.testuuid = testuuid;


    }
}