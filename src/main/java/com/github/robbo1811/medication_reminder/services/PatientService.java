package com.github.robbo1811.medication_reminder.services;


import com.github.robbo1811.medication_reminder.model.Patient;
import com.github.robbo1811.medication_reminder.repository.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

	@Autowired
	private PatientDao patientDao;


	public Optional<Patient> findByUsername(String username) {
		return patientDao.findByUsername(username);
	}

	public Optional<Patient> findById(Long patientId){
		return patientDao.findById(patientId);
	}

	public Patient save(Patient patient) {
		return patientDao.save(patient);
	}

	public List<Patient> findAll() {
		return patientDao.findAll();
	}
	

}
