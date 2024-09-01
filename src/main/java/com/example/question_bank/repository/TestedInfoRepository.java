package com.example.question_bank.repository;

import com.example.question_bank.entity.TestedInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestedInfoRepository extends JpaRepository<TestedInfo,String> {

    @Query(value = "SELECT correct_answer_count FROM tested_info WHERE uuid = ?1", nativeQuery = true)
    List<String> findIdByUuid1(String uuid);

    @Query(value = "SELECT uuid FROM tested_info WHERE uuid3 = ?1", nativeQuery = true)
    List<String> findIdByUuid(String uuid);

    @Query(value = "SELECT uuid FROM tested_info WHERE uuid3 = ?1", nativeQuery = true)
    List<String> findIdByUuidShare(String uuid);

    @Query(value = "SELECT uuid FROM tested_info WHERE uuid3 = ?1", nativeQuery = true)
    Optional<TestedInfo> findIdByUuid3(String uuid);

}
