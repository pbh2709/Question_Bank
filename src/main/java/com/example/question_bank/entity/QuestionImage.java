package com.example.question_bank.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class QuestionImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="uuid", length = 40)
    private String uuid;

    private String title;

    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;

    @Column(name="correct_answer")
    private int correct_answer;

    @Column(name="my_answer")
    private int my_answer;

    /**
     @Column(name = "created_at")
     private Date createdAt;

     @Column(name = "edited_at")
     private Date editedAt;
     */


    }
