package com.github.robbo1811.medication_reminder;

import com.github.robbo1811.medication_reminder.model.AuthenticationRequest;
import com.github.robbo1811.medication_reminder.model.Patient;
import com.github.robbo1811.medication_reminder.model.Role;
import com.github.robbo1811.medication_reminder.services.UserSecurityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.POST;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeEach
    public void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    public void close() {
        validatorFactory.close();
    }

    private AuthenticationRequest signupDTO = new AuthenticationRequest("patient10", "12345678", "Harry", "Wilson", "harry@email.com", new Role());
    private Patient patient = new Patient(signupDTO.getUsername(), signupDTO.getPassword(), signupDTO.getFirstname(), signupDTO.getLastname(), signupDTO.getEmail(), new Role());

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserSecurityService service;

    @Test
    public void signin(){
        restTemplate.postForEntity("/login/LoginUser", new AuthenticationRequest("admin", "myPass123"), Void.class);
        verify(this.service).signin("admin", "myPass123");
    }

    @Test
    public void registerUser(){
        when(service.signup(signupDTO.getUsername(), signupDTO.getPassword(), signupDTO.getFirstname(), signupDTO.getLastname(), signupDTO.getEmail())).thenReturn(Optional.of(patient));

        ResponseEntity<Patient> responseEntity = restTemplate.exchange("/login/RegisterUser", POST,
                new HttpEntity<>(signupDTO),
                Patient.class);

        assertThat(responseEntity.getStatusCode().value(), is(201));
        assertThat(responseEntity.getBody().getUsername(), is(patient.getUsername()));
        assertThat(responseEntity.getBody().getFirstname(), is(patient.getFirstname()));
        assertThat(responseEntity.getBody().getLastname(), is(patient.getLastname()));
        assertThat(responseEntity.getBody().getRoles().size(), is(patient.getRoles().size()));
    }

    @Test
    public void registerInvalidUser() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        when(service.signup(authenticationRequest.getUsername(), authenticationRequest.getPassword(), authenticationRequest.getFirstname(), authenticationRequest.getLastname(), authenticationRequest.getEmail())).thenReturn(Optional.of(patient));
        ResponseEntity<Patient> responseEntity = restTemplate.exchange("/login/RegisterUser", POST,
                new HttpEntity<>(authenticationRequest),
                Patient.class);

        assertThat(responseEntity.getStatusCode().value(), is(400));

    }
}
