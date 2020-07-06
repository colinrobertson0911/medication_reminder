package com.github.robbo1811.medication_reminder.controller;


import com.github.robbo1811.medication_reminder.model.AuthenticationRequest;
import com.github.robbo1811.medication_reminder.model.Patient;
import com.github.robbo1811.medication_reminder.model.User;
import com.github.robbo1811.medication_reminder.services.UserSecurityService;
import com.github.robbo1811.medication_reminder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	@Autowired
	UserSecurityService userSecurityService;

	@Autowired
	UserService userService;


	@PostMapping("/LoginUser")
	@ResponseStatus(HttpStatus.OK)
	public String loginUser(@RequestBody @Valid AuthenticationRequest authRequest) {
		return userSecurityService.signin(authRequest.getUsername(), authRequest.getPassword()).orElseThrow(()->
				new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));
	}

	@PostMapping("/RegisterUser")
	@ResponseStatus(HttpStatus.CREATED)
	public Patient registerUser(@RequestBody @Valid AuthenticationRequest authRequest) {
		return userSecurityService.signup(authRequest.getUsername(), authRequest.getPassword(), authRequest.getFirstname(), authRequest.getLastname(), authRequest.getEmail()).orElseThrow(() ->
				new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User already exists"));
	}

	@GetMapping("/Details/{username}")
	public ResponseEntity<User> userDetails(@PathVariable("username") String username) {
		Optional<User> user = userService.findByUsername(username);
		if (user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}

}
