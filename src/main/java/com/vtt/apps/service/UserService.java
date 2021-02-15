package com.vtt.apps.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vtt.apps.exception.ResourceNotFoundException;
import com.vtt.apps.exception.UserNotFoundException;
import com.vtt.apps.model.UserDetails;
import com.vtt.apps.repository.UserRepository;

@Service("userService")
public class UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	UserRepository userRepository;

	public Optional<UserDetails> fetchUserDetailsById(Long id){
		Optional<UserDetails> users =null;
		try {
			users = userRepository.findById(id);
			if (!users.isPresent())
				throw new UserNotFoundException("id-" +users.get().getId());
		}catch(Exception e ) {
			LOGGER.info("Unable to fetch the UserDetails by Id : "+id);
		}
		return users;
	}
	
	public Optional<List<UserDetails>> fetchAllUserDetails(){
		LOGGER.info("Exec fetchAllUsers ");
		Optional<List<UserDetails>>  users =null;
		try {
			users = Optional.of(userRepository.findAll());
		}catch(Exception e ) {
		}
		return users;
	}
	
	
	public Optional<UserDetails> update(final UserDetails users) {
		Optional<UserDetails> user =null;
		try {
			user= userRepository.findById(users.getId());
			if (!user.isPresent())
				throw new UserNotFoundException("id-" +user.get().getId());
			/*
			 * user.get().setName(users.getName());
			 * user.get().setUsername(users.getUsername());
			 * user.get().setPassword(users.getPassword());
			 * user.get().setRole(users.getRole());
			 */
			return Optional.ofNullable(userRepository.save(user.get()));
		}catch(Exception e ) {
			LOGGER.info("Unable to Update  the UserDetails by Id : "+users.getId());
			throw new ResourceNotFoundException("UserDetails", "id", users.getId());
		}
	}

	public ResponseEntity<?> delete(Long id){
		LOGGER.info("Exec deleteUsers "+id);
		UserDetails UserDetails =null;
		try {
			UserDetails = userRepository.findById(id).get();
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the UserDetails by Id : "+id);
			throw new ResourceNotFoundException("UserDetails", "id", id);
		}
		userRepository.delete(UserDetails);
		return ResponseEntity.ok().build();
	}
	
	public Optional<UserDetails> save(UserDetails user){
		System.err.println("Exec saveUsers () UserDetails :--> "+user);
		UserDetails tempUser =null;
		try{
			user.setCreatedDate(LocalDateTime.now());
			tempUser  = userRepository.save(user);
			if(tempUser ==null)
				LOGGER.info("Could nt save the User Details.!");
		}catch (Exception e) {
			throw e;
		}
		return Optional.of(tempUser);
	}



	public ResponseEntity<?> deleteUserByName(String userName){
		LOGGER.info("Exec deleteUsersByName "+userName);
		Optional<UserDetails> user = null;
		try {
			user = userRepository.findByUsername(userName);
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the UserDetails by User Name : "+userName);
			throw new ResourceNotFoundException("UserDetails", "userName", userName);
		}
		userRepository.delete(user.get());
		return ResponseEntity.ok().build();
	}



	public Optional<UserDetails> fetchByUsersName(String userName){
		LOGGER.info("Exec fetchByUsersByName "+userName);
		Optional<UserDetails> UserDetails=null;
		UserDetails = userRepository.findByUsername(userName);
		if(UserDetails !=null ) {
			LOGGER.info(" UserDetails :--> "+UserDetails);
			return UserDetails ;
		}else{
			return null;
		}
	}

	public Optional<UserDetails > fetchUsersByNamePassword(UserDetails user){
		LOGGER.info("Exec fetchByUsersByNamePassword"+user);
		String role ,userName,password ;
		role = userName = password =null;
		userName = user.getName();
		password = user.getPassword();
		Optional<UserDetails> tempUser = userRepository.findByUsername(userName);
		if(tempUser.isPresent() && (tempUser.get()).getPassword().equalsIgnoreCase(password)) {
			LOGGER.info(" UserDetails :--> "+tempUser);
		}
		else{
			LOGGER.info(" Invalid User  Credentails ");
			return null;
		}
		return tempUser;
	}


	/*
	 * public Optional<UserDetails> updatePassword(String userName, String newPassword) {
	 * Optional<UserDetails> user =null; Optional<UserDetails> updatedUser =null;
	 * 
	 * try { user = userRepository.findByUsername(userName);
	 * user.get().setPassword(newPassword); updatedUser =
	 * Optional.of(userRepository.save(user.get())); }catch(Exception e ) {
	 * LOGGER.info("Unable to Update  the userdetai+ls by userName: "+userName);
	 * throw new ResourceNotFoundException("UserDetails", "id", userName); } return
	 * updatedUser; }
	 */
}
