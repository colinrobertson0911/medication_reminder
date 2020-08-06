package com.github.robbo1811.medication_reminder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.robbo1811.medication_reminder.model.Medication;
import com.github.robbo1811.medication_reminder.services.MedicationService;
import com.github.robbo1811.medication_reminder.services.PatientService;
import com.github.robbo1811.medication_reminder.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@WithUserDetails("admin1")
public class MedicationControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    @Autowired
    MedicationService medicationService;

    MockMvc mockMvc;

    MockHttpSession session;

    final static String MEDICATION_ROOT_URI = "/medication";

    @BeforeEach
    public void setUp() {
        this.session = new MockHttpSession();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SharedHttpSessionConfigurer.sharedHttpSession())
                .build();
    }

    @Test
    public void getAllMedication() throws Exception {
        this.mockMvc.perform(get(MEDICATION_ROOT_URI + "/AllMedication")
                .session(session))
                .andExpect(status().isOk());
    }

    @Test
    public void addMedication() throws Exception {
        Medication medication = new Medication("Venlafaxine", "300mg", "Depression", 7, 2, null, 4, 124, false);
        this.mockMvc.perform(post(MEDICATION_ROOT_URI + "/AddMedication")
                .session(session)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(medication)))
                .andExpect(status().isCreated());
    }

    @Test
    public void editMedication() throws Exception {
        Medication medication = medicationService.findByNameAndDosage("Paracetamol", "250mg").get();
        medication.setDosage("500mg");
        ResultActions mvcResult = this.mockMvc.perform(patch(MEDICATION_ROOT_URI + "/EditMedication")
                .session(session)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(medication)))
                .andExpect(status().isOk());
        String expectedResult = "{\"medicationId\":1,\"name\":\"Paracetamol\",\"dosage\":\"500mg\",\"condition\":\"Sore Head\",\"timesAWeek\":7,\"timesADay\":4,\"timeToTake\":\"09:00:00\",\"quantity\":2,\"pillsLeft\":6,\"refill\":true}";
        Assertions.assertEquals(expectedResult, mvcResult.andReturn()
                .getResponse().getContentAsString());
    }

}
