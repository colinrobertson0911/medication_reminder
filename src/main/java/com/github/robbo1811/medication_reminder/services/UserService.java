package com.github.robbo1811.medication_reminder.services;

import com.github.robbo1811.medication_reminder.model.Patient;
import com.github.robbo1811.medication_reminder.model.Role;
import com.github.robbo1811.medication_reminder.model.User;
import com.github.robbo1811.medication_reminder.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    PatientService patientService;

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Patient updatePatient(long userId, String username, String firstname, String lastname, String email, String weight, String height, int age){
        Patient patient = patientService.findById(userId).get();
        if (username != null){
            patient.setUsername(username);
        }
        if (firstname != null){
            patient.setFirstname(firstname);
        }
        if (lastname != null) {
            patient.setLastname(lastname);
        }
        if (email != null) {
            patient.setEmail(email);
        }
        if (weight != null){
            patient.setWeight(weight);
        }
        if (height != null){
            patient.setHeight(height);
        }
        if (age != 0){
            patient.setAge(age);
        }
        return userDao.save(patient);
    }

}
