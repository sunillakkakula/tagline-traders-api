package com.vtt.apps.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtt.apps.exception.ResourceNotFoundException;
import com.vtt.apps.exception.UserNotFoundException;
import com.vtt.apps.model.CartDetails;
import com.vtt.apps.model.UserDetails;
import com.vtt.apps.repository.CartDetailsRepository;
import com.vtt.apps.repository.UserDetailsRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090")
public class UserDetailsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	UserDetailsRepository userDetailsRepository ;

	@Autowired
	CartDetailsRepository cartDetailsRepository ;
	/*
	 * Get All users
	 */	
	@GetMapping("/user")
	public List<UserDetails> retrieveAll() {
		LOGGER.info("Executing retrieveAll in UsersController");
		return  userDetailsRepository.findAll();
	}

	/* Create a new user */
	@PostMapping("/user")
	public UserDetails  create(@Valid @RequestBody UserDetails user ) {
		LOGGER.info("Executing create in UsersController user : "+user );
		user.setCartDetails(cartDetailsRepository.save(new CartDetails()));
		  return userDetailsRepository.save(user);
	}

	/* Validate a user */
	@PostMapping("/user/login")
	public Optional<UserDetails> validateUser(@Valid @RequestBody UserDetails user ) {
		LOGGER.info("Executing validateUser in UsersController");
		Optional<UserDetails> tempUser = userDetailsRepository.findByUserName(user.getUserName());
		if(tempUser.isPresent() && (tempUser.get()).getPassword().equalsIgnoreCase(user.getPassword())) {
			LOGGER.info(" UserDetails :--> "+tempUser);
		}
		if (!tempUser.isPresent())
			throw new UserNotFoundException("Invalid User Name and Password" );
		return tempUser;
	}

	/* Get a Single user by ID */
	@GetMapping("/user/{id}")
	public Optional<UserDetails> getUserById(@PathVariable(value = "id") Long userId) {
		LOGGER.info("Executing getUserById in UsersController");
		Optional<UserDetails> result = null;
		result = userDetailsRepository.findById(userId);
		if(result.isPresent()) {
			return result;
		}else {
			throw new ResourceNotFoundException("User Details" ,"ID", userId);
		}
	}


	/* Get a Single user by ID */
	@GetMapping("/user/profile/{id}")
	public Optional<UserDetails> getUserProfileById(@PathVariable(value = "id") Long userId) {
		LOGGER.info("Executing getUserProfileById in UsersController");
		Optional<UserDetails> result = null;
		result = userDetailsRepository.findById(userId);
		if(result.isPresent()) {
			return result;
		}else {
			throw new ResourceNotFoundException("User Details" ,"ID", userId);
		}
	}

	
	/* Update a user */
	@PutMapping("/user/{id}")
	public UserDetails update(@PathVariable Long id,
			@Valid @RequestBody UserDetails userUpdt) {
		LOGGER.info("Executing update in UsersController");
		
		if(!userDetailsRepository.existsById(id)) { throw new
			ResourceNotFoundException("UserDetails","ID" ,id); }
		
		return userDetailsRepository.findById(id) .map(userDetails -> {
			userDetails.setName(userUpdt.getName());
			userDetails.setContactNo(userUpdt.getContactNo());
			userDetails.setEmailId(userUpdt.getEmailId());
			userDetails.setPassword(userUpdt.getPassword());
			userDetails.setRole(userUpdt.getRole());
			userDetails.setUserName(userUpdt.getUserName());
			
			return userDetailsRepository.save(userDetails); })
				.orElseThrow(() -> new ResourceNotFoundException("UserDetails ","id ",id)); 
	}

	/* Delete a user */
	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		LOGGER.info("Executing /user/{id} in UsersController");
		userDetailsRepository.deleteById(id);
	}
}


