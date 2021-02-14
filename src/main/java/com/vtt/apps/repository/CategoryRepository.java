package com.vtt.apps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtt.apps.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Optional<Category> findById(Long id);
	public Optional<Category> findByName(String name);
}