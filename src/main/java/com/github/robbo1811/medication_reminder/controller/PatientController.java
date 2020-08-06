package com.github.robbo1811.medication_reminder.controller;


import com.github.robbo1811.medication_reminder.model.Medication;
import com.github.robbo1811.medication_reminder.model.Patient;
import com.github.robbo1811.medication_reminder.services.MedicationService;
import com.github.robbo1811.medication_reminder.services.PatientService;
import com.github.robbo1811.medication_reminder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

	@Autowired
	PatientService patientService;

	@Autowired
	UserService userService;

	@GetMapping("/AllPatients")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Patient>> allPatients(){
		return ResponseEntity.ok(patientService.findAll());
	}


	@PatchMapping("/EditPatient")
	public ResponseEntity<Patient> editPatient(@RequestBody Patient patient){
		return ResponseEntity.ok(userService.updatePatient(patient.getUserId(), patient.getUsername(), patient.getFirstname(), patient.getLastname(), patient.getEmail(), patient.getWeight(), patient.getHeight(), patient.getAge()));
	}

	@GetMapping("/MyMedication/{username}")
	public ResponseEntity<List<Medication>> myMedication(@PathVariable("username")String username) {
		Optional<Patient> optionalPatient = patientService.findByUsername(username);
		Patient patient = optionalPatient.get();
		List<Medication> myMedication = patient.getMedication();
		if (patient.getMedication().isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(myMedication, HttpStatus.OK);
	}

}
