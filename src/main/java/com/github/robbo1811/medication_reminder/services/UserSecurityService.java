package com.github.robbo1811.medication_reminder.services;


import com.github.robbo1811.medication_reminder.model.Patient;
import com.github.robbo1811.medication_reminder.model.Role;
import com.github.robbo1811.medication_reminder.model.User;
import com.github.robbo1811.medication_reminder.repository.PatientDao;
import com.github.robbo1811.medication_reminder.repository.RoleDao;
import com.github.robbo1811.medication_reminder.repository.UserDao;
import com.github.robbo1811.medication_reminder.security.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSecurityService.class);

    private UserDao userDao;

    private PatientDao patientDao;

    private AuthenticationManager authenticationManager;

    private RoleDao roleDao;

    private PasswordEncoder passwordEncoder;

    private JwtProvider jwtProvider;

    public UserSecurityService(UserDao userDao, PatientDao patientDao, AuthenticationManager authenticationManager, RoleDao roleDao, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userDao = userDao;
        this.patientDao = patientDao;
        this.authenticationManager = authenticationManager;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    /**
     * Sign in a user into the application, with JWT-enabled authentication
     *
     * @param username  username
     * @param password  password
     * @return Optional of the Java Web Token, empty otherwise
     */
    public Optional<String> signin(String username, String password){
        LOGGER.info("New user attempting to sign in");
        Optional<String> token = Optional.empty();
        Optional<User> user = userDao.findByUsername(username);
        if (user.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                token = Optional.of(jwtProvider.createToken(username, user.get().getRoles()));
            }catch (AuthenticationException e) {
                LOGGER.info("Log in failed for user {}", username);
            }
        }
        return token;
    }

    /**
     * Create a new user in the database.
     *
     * @param username username
     * @param password password
     * @param firstname first name
     * @param lastname last name
     * @return Optional of user, empty if the user already exists.
     */
    public Optional<Patient> signup(String username, String password, String firstname, String lastname, String email){
        LOGGER.info("New user attempting to sign in");
        Optional<Patient> patient = Optional.empty();
        if (!userDao.findByUsername(username).isPresent()) {
            Optional<Role> role = roleDao.findByRoleName("ROLE_PATIENT");
            patient = Optional.of(patientDao.save(new Patient(username,
                    passwordEncoder.encode(password),
                    firstname,
                    lastname,
                    email,
                    role.get())));
        }
        return patient;
    }
}
