package com.github.robbo1811.medication_reminder.repository;

import com.github.robbo1811.medication_reminder.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationDao extends JpaRepository<Medication, Long> {

	List<Medication> findAll();

	Optional<Medication> findByNameAndDosage(String name, String dosage);

	Optional<Medication> findByName(String name);

	Medication findById(long medicationId);

}
