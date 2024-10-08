package com.example.question_bank.repository;

import com.example.question_bank.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {

    @Query(value = "SELECT UUID FROM Question q WHERE UUID = ?1", nativeQuery = true)
    List<String> findIdByUuid(String uuid);



}
