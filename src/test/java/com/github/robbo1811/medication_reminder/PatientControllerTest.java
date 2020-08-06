package com.github.robbo1811.medication_reminder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.robbo1811.medication_reminder.model.Patient;
import com.github.robbo1811.medication_reminder.model.Role;
import com.github.robbo1811.medication_reminder.repository.RoleDao;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@WithUserDetails("admin1")
public class PatientControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    @Autowired
    PatientService patientService;

    @Autowired
    RoleDao roleDao;

    @Autowired
    MedicationService medicationService;

    MockMvc mockMvc;

    MockHttpSession session;

    final static String PATIENT_ROOT_URI = "/patient";

    @BeforeEach
    public void setUp() {
        this.session = new MockHttpSession();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SharedHttpSessionConfigurer.sharedHttpSession())
                .build();
    }

    @Test
    public void getAListOfAllPatients() throws Exception {
        this.mockMvc.perform(get(PATIENT_ROOT_URI + "/AllPatients")
                .session(session))
                .andExpect(status().isOk());
    }

    @Test
    public void editPatient() throws Exception {
        Patient patient = patientService.findById(2L).get();
        patient.setWeight("90Kg");
        ResultActions mvcResult = this.mockMvc.perform(patch(PATIENT_ROOT_URI + "/EditPatient")
                .session(session)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk());
        String expectedResult = "{\"userId\":2,\"username\":\"patient1\",\"firstname\":\"Peter\",\"lastname\":\"Smith\",\"email\":\"peter@email.com\",\"roles\":[{\"roleId\":2,\"roleName\":\"ROLE_PATIENT\",\"authority\":\"ROLE_PATIENT\"}],\"weight\":\"90Kg\",\"height\":\"180cm\",\"age\":40,\"medication\":[{\"medicationId\":1,\"name\":\"Paracetamol\",\"dosage\":\"250mg\",\"condition\":\"Sore Head\",\"timesAWeek\":7,\"timesADay\":4,\"timeToTake\":\"2012-03-17T09:00:00.000+0000\",\"quantity\":2,\"pillsLeft\":6,\"refill\":false},{\"medicationId\":3,\"name\":\"Candesartan\",\"dosage\":\"8mg\",\"condition\":\"High Blood Pressure\",\"timesAWeek\":7,\"timesADay\":1,\"timeToTake\":\"2012-03-17T09:00:00.000+0000\",\"quantity\":1,\"pillsLeft\":30,\"refill\":false},{\"medicationId\":5,\"name\":\"Mirtazapine\",\"dosage\":\"30mg\",\"condition\":\"Depression\",\"timesAWeek\":7,\"timesADay\":1,\"timeToTake\":\"2012-03-17T09:00:00.000+0000\",\"quantity\":2,\"pillsLeft\":30,\"refill\":false},{\"medicationId\":7,\"name\":\"iBuprofen\",\"dosage\":\"200mg\",\"condition\":\"Inflammation \",\"timesAWeek\":7,\"timesADay\":2,\"timeToTake\":\"2012-03-17T09:00:00.000+0000\",\"quantity\":4,\"pillsLeft\":32,\"refill\":false},{\"medicationId\":9,\"name\":\"Levothyroxine\",\"dosage\":\"50mg\",\"condition\":\"Hyper Thyroid \",\"timesAWeek\":7,\"timesADay\":1,\"timeToTake\":\"2012-03-17T09:00:00.000+0000\",\"quantity\":1,\"pillsLeft\":56,\"refill\":false}]}";

        Assertions.assertEquals(expectedResult, mvcResult.andReturn()
                .getResponse().getContentAsString());
    }

    @Test
    public void getMyMedication() throws Exception {
        this.mockMvc.perform(get(PATIENT_ROOT_URI + "/MyMedication/patient1")
                .session(session))
                .andExpect(status().isOk());
    }

}
