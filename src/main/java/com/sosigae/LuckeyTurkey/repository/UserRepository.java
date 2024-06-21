package com.sosigae.LuckeyTurkey.repository;

import com.sosigae.LuckeyTurkey.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByNameAndPhone(String name, String phone);

    User findByUserId(int userId);
}
