package com.example.question_bank.repository;

import com.example.question_bank.entity.User;
import com.example.question_bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

   Boolean existsByUsername(String username);
   Boolean existsByEmail(String email);
    User findByusername(String username);

    User findByemail(String email);

    @Query(value = "SELECT * FROM User u WHERE u.originalId = ?1", nativeQuery = true)
    Optional<User>  findByOgId(int id);


}
