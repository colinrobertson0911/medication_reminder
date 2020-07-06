package com.github.robbo1811.medication_reminder.services;


import com.github.robbo1811.medication_reminder.model.Medication;
import com.github.robbo1811.medication_reminder.repository.MedicationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service(value = "medicationService")
public class MedicationService {

	@Autowired
	private MedicationDao medicationDao;

	public Optional<Medication> findByNameAndDosage(String name, String dosage) {
		return medicationDao.findByNameAndDosage(name, dosage);
	}

	public Page<Medication> findAll(int page, int size) {
		Pageable pageRequest = PageRequest.of(page, size);
		return medicationDao.findAll(pageRequest);
	}

	public Medication save(Medication medication) {
		return medicationDao.save(medication);
	}

	public Optional<Medication> retrieveByName(String name) {
		return medicationDao.findByName(name);
	}

	public Optional<Medication> retrieveById(Long medicationId) {
		return medicationDao.findById(medicationId);
	}
	
	public int removePillsFromPillsLeft(int pillsLeft, Long medicationId) {
		Medication medication = retrieveById(medicationId).get();
		Date timeTaken = medication.getTimeToTake();
		int totalPills = medication.getPillsLeft();
		int pillsTaken = medication.getQuantity();
		if (timeTaken == medication.getTimeToTake()) {
			pillsLeft = totalPills - pillsTaken;
		}
		return pillsLeft;
	}

	public boolean refillReminder(int pillsLeft, Long medicationId) {
		Medication medication = retrieveById(medicationId).get();
		int totalPills = medication.getPillsLeft();
		if (totalPills < 7) {
			return true;
		}
		return false;
	}

	public Medication update(long medicationId, String name, String dosage) {
		Medication medication =	retrieveById(medicationId).get();
		if (name != null) {
			medication.setName(name);
		}
		if (dosage != null) {
			medication.setDosage(dosage);
		}
		return medicationDao.save(medication);
	}

}
