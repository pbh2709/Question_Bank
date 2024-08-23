package com.example.question_bank.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer originalId;

    private String username;
    private String password;
    private String email;
    private String roles;

    @CreationTimestamp
    private Date createdAt;
    @Builder
    public User(String username, String password, String roles,String email) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }
}

