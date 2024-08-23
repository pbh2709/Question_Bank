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
public class Questions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", length = 40)
    private String uuid;
    private  String title;

    private String my_answer;


    //private Date createdAt;

    @Builder
    public Questions(String title) {
        this.title = title;



    }




}
