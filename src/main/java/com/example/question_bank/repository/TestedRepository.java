package com.example.question_bank.repository;

import com.example.question_bank.entity.Question;
import com.example.question_bank.entity.Tested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestedRepository extends JpaRepository<Tested, Long> {
    @Query(value = "SELECT t.id FROM tested t WHERE t.t_uuid = ?1", nativeQuery = true)
    List<Long> findIdByUuid(String uuid);

    @Query(value = "SELECT t.id FROM tested t WHERE t.t1_uuid = ?1", nativeQuery = true)
    List<Long> findIdByUuid1(String uuid);

    @Query(value = "SELECT t.id FROM tested t WHERE t.q_uuid = ?1", nativeQuery = true)
    List<Long> findIdByQuestionUuid(String uuid);

    @Query(value = "SELECT t.id FROM tested t WHERE t.qj_uuid = ?1", nativeQuery = true)
    List<Long> findIdByQuestionjUuid(String uuid);

    @Query(value = "SELECT t.id FROM tested t WHERE t.qi_uuid = ?1", nativeQuery = true)
    List<Long> findIdByQuestionimageUuid(String uuid);



}

