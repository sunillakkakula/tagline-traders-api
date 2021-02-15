package com.vtt.apps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtt.apps.model.UserDetails;


@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
	public Optional<String> findUserRoleById(Long id);
	public Optional<UserDetails> findByUsername(String userName);
}