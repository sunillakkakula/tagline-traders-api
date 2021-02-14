package com.vtt.apps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtt.apps.model.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	public Optional<String> findUserRoleById(Long id);
	public Optional<Users> findByUsername(String userName);
}