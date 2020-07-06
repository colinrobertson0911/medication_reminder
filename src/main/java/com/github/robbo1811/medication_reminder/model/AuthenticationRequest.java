package com.github.robbo1811.medication_reminder.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

public class AuthenticationRequest {

    @NotNull
    private String username;

    @NotNull
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    private String password;

    private String firstname;

    private String lastname;

    private String email;

    private List<Role> roles;


    public AuthenticationRequest() {

    }

    public AuthenticationRequest(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequest(String username, String password, String firstname, String lastname) {
        super();
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public AuthenticationRequest(String username, String password, String firstname, String lastname, String email, Role role) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.roles = Arrays.asList(role);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
