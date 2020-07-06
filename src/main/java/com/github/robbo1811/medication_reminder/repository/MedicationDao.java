package com.github.robbo1811.medication_reminder.repository;

import com.github.robbo1811.medication_reminder.model.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationDao extends JpaRepository<Medication, Long> {

	Page<Medication> findAll(Pageable pageable);

	Optional<Medication> findByNameAndDosage(String name, String dosage);

	Optional<Medication> findByName(String name);

	Medication findById(long medicationId);

}
