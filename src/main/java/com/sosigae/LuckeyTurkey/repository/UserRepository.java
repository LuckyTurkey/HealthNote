package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByNameAndPhone(String name, String phone);

    // pk로 찾기
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    User findUserByUserId(@Param("userId") int userId);

    // id로 찾기
    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") String id);
}
