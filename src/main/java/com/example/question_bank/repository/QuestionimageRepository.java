package com.example.question_bank.repository;

import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.QuestionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionimageRepository extends JpaRepository<QuestionImage, String> {



}
