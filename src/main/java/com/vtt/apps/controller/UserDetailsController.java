package com.vtt.apps.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vtt.apps.exception.UserNotFoundException;
import com.vtt.apps.model.UserDetails;
import com.vtt.apps.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090")
public class UserDetailsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;

	// Get All users
	@GetMapping("/user")
	public Optional<List<UserDetails>> retrieveAll() {
		LOGGER.info("Executing retrieveAll in UsersController");
		Optional<List<UserDetails>> usersList = null;
		usersList = userService.fetchAllUserDetails();
		return  usersList;
	}

	// Create a new user
	@PostMapping("/user")
	public ResponseEntity<Object>  createUser(@Valid @RequestBody UserDetails user ) {
		LOGGER.info("Executing createUsers in UsersController user : "+user );


		LOGGER.info("user : "+user);
		System.err.println("******* user *********: "+user);
		Optional<UserDetails> createdUser = userService.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdUser.get().getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	// Validate a user
	@PostMapping("/user/login")
	public Optional<UserDetails> validateUser(@Valid @RequestBody UserDetails user ) {
		LOGGER.info("Executing validateUser in UsersController");
		Optional<UserDetails> tempUser = null;
		tempUser  = userService.fetchUsersByNamePassword(user);
		if (!tempUser.isPresent())
			throw new UserNotFoundException("id-" +user.getName());
		return tempUser;
	}

	// Get a Single user by ID
	@GetMapping("/user/{id}")
	public Optional<UserDetails>   getUserById(@PathVariable(value = "id") Long userId) {
		LOGGER.info("Executing getUserById in UsersController");
		Optional<UserDetails> user = userService.fetchUserDetailsById(userId);
		if (!user.isPresent())
			throw new UserNotFoundException("id-" +userId);
		return user ;
	}


	// Update a user
	@PutMapping("/user/{id}")
	public Optional<UserDetails> update(@PathVariable Long id,
			@Valid @RequestBody UserDetails userUpdt) {
		LOGGER.info("Executing updateUsers in UsersController");
		Optional<UserDetails> user = userService.fetchUserDetailsById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("id-" +id);
		user.get().setName(userUpdt.getName());
		user.get().setPassword(userUpdt.getPassword());
		user.get().setRole(userUpdt.getRole());
		user.get().setUsername(userUpdt.getUsername());
		return userService.save(user.get());
	}

	// Delete a user
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		LOGGER.info("Executing /user/{id} in UsersController");
		return userService.delete(id);
	}

	// Delete a user
	@DeleteMapping("/user/{name}")
	public ResponseEntity<?> deleteUsersByName(@PathVariable(value = "name") String name) {
		LOGGER.info("Executing /user/{userName} in UsersController");
		return userService.deleteUserByName(name);
	}


}


