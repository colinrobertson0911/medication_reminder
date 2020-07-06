package com.github.robbo1811.medication_reminder.repository;

import com.github.robbo1811.medication_reminder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
