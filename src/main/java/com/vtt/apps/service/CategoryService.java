package com.vtt.apps.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vtt.apps.exception.CategoryNotFoundException;
import com.vtt.apps.exception.ResourceNotFoundException;
import com.vtt.apps.model.Category;
import com.vtt.apps.repository.CategoryRepository;

@Service("categoryService")
public class CategoryService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	CategoryRepository categoryRepository;

	public Optional<Category> fetchCategoryById(Long id){
		Optional<Category> category =null;
		try {
			category = categoryRepository.findById(id);
			if (!category.isPresent())
				throw new CategoryNotFoundException("id-" +category.get().getId());
		}catch(Exception e ) {
			LOGGER.info("Unable to fetch the Category by Id : "+id);
		}
		return category;
	}
	
	public Optional<List<Category>> fetchAll(){
		LOGGER.info("Exec fetchAll ");
		Optional<List<Category>>  categories =null;
		try {
			categories = Optional.of(categoryRepository.findAll());
		}catch(Exception e ) {
		}
		return categories;
	}
	
	
	public Optional<Category> update(final Category category) {
		Optional<Category> foundCategory =null;
		return Optional.ofNullable(categoryRepository.save(category));
	}

	public ResponseEntity<?> delete(Long id){
		LOGGER.info("Exec delete "+id);
		Category Category =null;
		try {
			Category = categoryRepository.findById(id).get();
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the Category by Id : "+id);
			throw new ResourceNotFoundException("Category", "id", id);
		}
		categoryRepository.delete(Category);
		return ResponseEntity.ok().build();
	}
	
	public Optional<Category> save(Category category){
		System.err.println("ABOUT to Exec save () Category :--> "+category);
		Category tempCategory =null;
		try{
			tempCategory  = categoryRepository.save(category);
			System.out.println("Executed save () Category :--> "+tempCategory);
		}catch (Exception e) {
			throw e;
		}
		return Optional.of(tempCategory);
	}



	public ResponseEntity<?> deleteByName(String name){
		LOGGER.info("Exec deleteByName "+name);
		Optional<Category> category = null;
		try {
			category = categoryRepository.findByName(name);
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the Category by Name : "+name);
			throw new ResourceNotFoundException("Category", " Name", name);
		}
		categoryRepository.delete(category.get());
		return ResponseEntity.ok().build();
	}



	public Optional<Category> fetchByName(String name){
		LOGGER.info("Exec fetchByByName "+name);
		Optional<Category> Category=null;
		Category = categoryRepository.findByName(name);
		if(Category !=null ) {
			LOGGER.info(" Category :--> "+Category);
			return Category ;
		}else{
			return null;
		}
	}


	/*
	 * public Optional<Category> updatePassword(String name, String newPassword) {
	 * Optional<Category> category =null; Optional<Category> updatedUser =null;
	 * 
	 * try { category = categoryRepository.findByUsername(name);
	 * category.get().setPassword(newPassword); updatedUser =
	 * Optional.of(categoryRepository.save(category.get())); }catch(Exception e ) {
	 * LOGGER.info("Unable to Update  the userdetai+ls by name: "+name);
	 * throw new ResourceNotFoundException("Category", "id", name); } return
	 * updatedUser; }
	 */
}
