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
import com.vtt.apps.model.Users;
import com.vtt.apps.repository.UserRepository;

@Service("userService")
public class UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	UserRepository userRepository;

	public Optional<Users> fetchUserById(Long id){
		Optional<Users> users =null;
		try {
			users = userRepository.findById(id);
			if (!users.isPresent())
				throw new UserNotFoundException("id-" +users.get().getId());
		}catch(Exception e ) {
			LOGGER.info("Unable to fetch the Users by Id : "+id);
		}
		return users;
	}
	
	public Optional<List<Users>> fetchAllUsers(){
		LOGGER.info("Exec fetchAllUsers ");
		Optional<List<Users>>  users =null;
		try {
			users = Optional.of(userRepository.findAll());
		}catch(Exception e ) {
		}
		return users;
	}
	
	
	public Optional<Users> update(final Users users) {
		Optional<Users> user =null;
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
			LOGGER.info("Unable to Update  the Users by Id : "+users.getId());
			throw new ResourceNotFoundException("Users", "id", users.getId());
		}
	}

	public ResponseEntity<?> deleteUser(Long id){
		LOGGER.info("Exec deleteUsers "+id);
		Users Users =null;
		try {
			Users = userRepository.findById(id).get();
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the Users by Id : "+id);
			throw new ResourceNotFoundException("Users", "id", id);
		}
		userRepository.delete(Users);
		return ResponseEntity.ok().build();
	}
	
	public Optional<Users> save(Users user){
		System.err.println("Exec saveUsers () Users :--> "+user);
		Users tempUser =null;
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
		Optional<Users> user = null;
		try {
			user = userRepository.findByUsername(userName);
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the Users by User Name : "+userName);
			throw new ResourceNotFoundException("Users", "userName", userName);
		}
		userRepository.delete(user.get());
		return ResponseEntity.ok().build();
	}



	public Optional<Users> fetchByUsersName(String userName){
		LOGGER.info("Exec fetchByUsersByName "+userName);
		Optional<Users> Users=null;
		Users = userRepository.findByUsername(userName);
		if(Users !=null ) {
			LOGGER.info(" Users :--> "+Users);
			return Users ;
		}else{
			return null;
		}
	}

	public Optional<Users > fetchUsersByNamePassword(Users user){
		LOGGER.info("Exec fetchByUsersByNamePassword"+user);
		String role ,userName,password ;
		role = userName = password =null;
		userName = user.getName();
		password = user.getPassword();
		Optional<Users> tempUser = userRepository.findByUsername(userName);
		if(tempUser.isPresent() && (tempUser.get()).getPassword().equalsIgnoreCase(password)) {
			LOGGER.info(" Users :--> "+tempUser);
		}
		else{
			LOGGER.info(" Invalid User  Credentails ");
			return null;
		}
		return tempUser;
	}


	/*
	 * public Optional<Users> updatePassword(String userName, String newPassword) {
	 * Optional<Users> user =null; Optional<Users> updatedUser =null;
	 * 
	 * try { user = userRepository.findByUsername(userName);
	 * user.get().setPassword(newPassword); updatedUser =
	 * Optional.of(userRepository.save(user.get())); }catch(Exception e ) {
	 * LOGGER.info("Unable to Update  the userdetai+ls by userName: "+userName);
	 * throw new ResourceNotFoundException("Users", "id", userName); } return
	 * updatedUser; }
	 */
}
