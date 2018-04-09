package com.nau.icit.repository;

import com.nau.icit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
}

