package com.github.robbo1811.medication_reminder.repository;

import com.github.robbo1811.medication_reminder.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String name);

    List<Role> findAll();
}
