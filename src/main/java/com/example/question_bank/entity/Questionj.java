package com.example.question_bank.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@Entity
@ToString
public class Questionj extends BaseEntity {

    @Id
    private String id;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", length = 40)
    private String uuid;
    private  String title;


    private  String correct_answer;
    private  int my_answer;



    //private Date createdAt;

    @Builder
    public Questionj(String title,  String correct_answer, int my_answer) {
        this.title = title;

        this.correct_answer = correct_answer;
        this.my_answer = my_answer;
    }




}
