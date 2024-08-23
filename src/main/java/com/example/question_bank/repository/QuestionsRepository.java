package com.example.question_bank.repository;

import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions,String> {
}
