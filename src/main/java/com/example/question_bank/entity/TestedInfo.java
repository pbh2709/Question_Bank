package com.example.question_bank.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@Entity
public class TestedInfo extends BaseEntity1{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;  //객관식

    @Column(name="uuid0") //이미지
    private String uuid0;

    @Column(name="uuid1") //주관식
    private String uuid1;

    @Column(name="uuid2") //서술형
    private String uuid2;


    @Column(name="uuid3") //공용
    private String uuid3;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "retest")
    private String retest;

    @Column(name = "grading")
    private String grading;

    @Column(name = "questionimage_count")
    private int questionimageCount;

    @Column(name = "question_count")
    private int questionCount;

    @Column(name = "questionj_count")
    private int questionjCount;

    @Column(name = "questions_count")
    private int questionsCount;

    @Column(name = "correctimage_answer_count")
    private int correctimageAnswerCount;

    @Column(name = "correct_answer_count")
    private int correctAnswerCount;

    @Column(name = "correctj_answer_count")
    private int correctjAnswerCount;

    @Column(name = "correctimage_answer_score")
    private int correctimageAnswerScore;

    @Column(name = "correct_answer_score")
    private int correctAnswerScore;

    @Column(name = "correctj_answer_score")
    private int correctjAnswerScore;

    @Column(name = "corrects_answer_score")
    private String correctsAnswerScore;

    @Column(name = "scoreTotal")
    private int scoreTotal;


    @Builder
    public TestedInfo(String uuid,String uuid0,String uuid1,String uuid2,String uuid3,String retest,
                      String grading, String userName, int questionCount,int questionimageCount,
                      int correctimageAnswerCount ,int correctAnswerCount, int questionjCount,
                      int correctimageAnswerScore ,int questionsCount,String correctsAnswerScore,
                      int correctjAnswerCount) {
        this.uuid1=uuid1;
        this.uuid0=uuid0;
        this.uuid = uuid;
        this.uuid2 = uuid2;
        this.uuid3 = uuid3;
        this.retest = retest;
        this.grading= grading;
        this.userName = userName;
        this.questionCount = questionCount;
        this.correctAnswerCount = correctAnswerCount;
        this.correctimageAnswerCount = correctimageAnswerCount;
        this.questionjCount = questionjCount;
        this.correctjAnswerCount = correctjAnswerCount;
        this.correctsAnswerScore = correctsAnswerScore;
        this.correctimageAnswerScore = correctimageAnswerScore;

    }
}
