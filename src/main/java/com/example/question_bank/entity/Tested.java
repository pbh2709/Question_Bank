package com.example.question_bank.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
public class Tested {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "t_uuid")  //문제 유형별
    private String testedUuid;

    @Column(name = "t1_uuid") //공용
    private String testedShareUuid;




    private int answer;

    private int answerimage;

    private String answerj;

    private String answers;

    @ManyToOne
    @JoinColumn(name = "q_uuid")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "qj_uuid")
    private Questionj questionj;

    @ManyToOne
    @JoinColumn(name = "qs_uuid")
    private Questions questions;

    @ManyToOne
    @JoinColumn(name = "qi_uuid")
    private QuestionImage questionimage;
}

