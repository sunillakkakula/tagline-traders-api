package com.vtt.apps.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtt.apps.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	public Optional<Product> findById(Long id);
	public Optional<Product> findByName(String name);
	public Set<Product> findBySubCategoryId(Long subCategoryId); 
	public Set<Product> findByIsVttBestSeller(Boolean flag);
}