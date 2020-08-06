package com.github.robbo1811.medication_reminder.controller;


import com.github.robbo1811.medication_reminder.model.Medication;
import com.github.robbo1811.medication_reminder.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medication")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicationController {

	@Autowired
	private MedicationService medicationService;

	@GetMapping("/AllMedication")
	public ResponseEntity<List<Medication>> allMedication() {
		return ResponseEntity.ok(medicationService.findAll()) ;
	}


	@PostMapping("/AddMedication")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Medication> addMedicationSubmit(@RequestBody Medication medication) {
		Optional<Medication> medicationDb = medicationService.findByNameAndDosage(medication.getName(), medication.getDosage());
		if (medicationDb.isPresent()) {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
		medicationService.save(medication);
		return new ResponseEntity<>(medication, HttpStatus.CREATED);
	}


	@PatchMapping("EditMedication")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Medication> updateMedication(@RequestBody Medication medication) {
		return ResponseEntity.ok(medicationService.update(medication.getMedicationId(), medication.getName(), medication.getDosage(), medication.getCondition(), medication.getTimesAWeek(), medication.getTimesADay(), medication.getQuantity()));
	}

}
