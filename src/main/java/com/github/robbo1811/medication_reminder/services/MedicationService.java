package com.github.robbo1811.medication_reminder.services;


import com.github.robbo1811.medication_reminder.model.Medication;
import com.github.robbo1811.medication_reminder.repository.MedicationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service(value = "medicationService")
public class MedicationService {

	@Autowired
	private MedicationDao medicationDao;

	public Optional<Medication> findByNameAndDosage(String name, String dosage) {
		return medicationDao.findByNameAndDosage(name, dosage);
	}

	public List<Medication> findAll() {
		return medicationDao.findAll();
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
		LocalTime timeTaken = medication.getTimeToTake();
		int totalPills = medication.getPillsLeft();
		int pillsTaken = medication.getQuantity();
		if (timeTaken == medication.getTimeToTake()) {
			pillsLeft = totalPills - pillsTaken;
		}
		return pillsLeft;
	}

	public boolean refillReminder(Long medicationId) {
		Medication medication = retrieveById(medicationId).get();
		int totalPills = medication.getPillsLeft();
		if (totalPills < 7) {
			return true;
		}
		return false;
	}

	public Medication update(long medicationId, String name, String dosage, String condition, int timesAWeek, int timesADay, int quantity) {
		Medication medication =	retrieveById(medicationId).get();
		if (name != null) {
			medication.setName(name);
		}
		if (dosage != null) {
			medication.setDosage(dosage);
		}
		if (condition != null) {
			medication.setCondition(condition);
		}
		if (timesAWeek != 0) {
			medication.setTimesAWeek(timesAWeek);
		}
		if (timesADay != 0) {
			medication.setTimesADay(timesADay);
		}
		if (quantity != 0) {
			medication.setQuantity(quantity);
		}

		return medicationDao.save(medication);
	}

}
