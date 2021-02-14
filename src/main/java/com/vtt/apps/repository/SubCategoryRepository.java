package com.vtt.apps.repository;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vtt.apps.model.SubCategory;


@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
	public Optional<SubCategory> findById(Long id);
	public Optional<SubCategory> findByName(String name);
	Set<SubCategory> findByCategoryId(Long categoryId); 
}