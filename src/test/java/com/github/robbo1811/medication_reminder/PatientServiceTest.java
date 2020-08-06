package com.github.robbo1811.medication_reminder;

import com.github.robbo1811.medication_reminder.model.Patient;
import com.github.robbo1811.medication_reminder.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PatientServiceTest {

    @Autowired
    PatientService patientService;

    @Test
    void test_FindByUsernameWhenUserExists() {
        Patient patient = patientService.findByUsername("patient1").get();
        assertEquals(2, patient.getUserId());
    }

    @Test
    public void test_RetrieveInvalidUser() {
        Optional<Patient> patient = patientService.findByUsername("unknown");
        assertTrue(patient.isEmpty());
    }


}
