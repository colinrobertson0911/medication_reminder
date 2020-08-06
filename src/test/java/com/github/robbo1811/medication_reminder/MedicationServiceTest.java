package com.github.robbo1811.medication_reminder;

import com.github.robbo1811.medication_reminder.model.Medication;
import com.github.robbo1811.medication_reminder.services.MedicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MedicationServiceTest {

    @Autowired
    MedicationService medicationService;

    @Test
    public void test_ThatMedicationCanBeRetrievedUsingAnId() {
        Optional<Medication> medicationOpt = medicationService.retrieveById(2L);
        Medication medication = medicationOpt.get();
        assertEquals("Amlodipine", medication.getName());

    }

    @Test
    void test_findMedicationByNameAndDosage() {
        Medication medication = medicationService.findByNameAndDosage("Paracetamol", "250mg").get();
        assertEquals(1, medication.getMedicationId());

    }

    @Test
    public void test_ThatMedicationCanBeRetrievedByName() {
        Medication medication = medicationService.retrieveByName("Paracetamol").get();
        Medication medicationFromDatabase = medicationService.retrieveByName(medication.getName()).get();
        assertEquals(medication, medicationFromDatabase);

    }

    @Test
    public void test_ThatPillsLeftIsCalculatedOnceTimeToTakeHasBeenReached() {
        int pillsLeft = medicationService.removePillsFromPillsLeft(100, 6L);
        System.out.println(pillsLeft);
        assertNotEquals(pillsLeft, 100);

    }

    @Test
    public void test_IfPillsLeftDropsBelowCertainAmount_ItTriggersTheRefillReminder() {
        boolean refill = medicationService.refillReminder(1L);
        assertEquals(refill, true);
    }

}
