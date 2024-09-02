package com.example.question_bank.repository;

import com.example.question_bank.entity.Questionj;
import com.example.question_bank.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions,String> {

    @Query(value = "SELECT UUID FROM Questions qs WHERE UUID = ?1", nativeQuery = true)
    List<String> findIdByUuid(String uuid);
}
