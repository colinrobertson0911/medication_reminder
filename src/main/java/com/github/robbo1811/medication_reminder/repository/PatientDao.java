package com.github.robbo1811.medication_reminder.repository;

import com.github.robbo1811.medication_reminder.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientDao extends JpaRepository<Patient, Long> {

	Optional<Patient> findByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
	
	Optional<Patient> findByUsername(@Param("username") String username);

	Optional<Patient> findById(@Param("patientId") long patientId);
	
}
