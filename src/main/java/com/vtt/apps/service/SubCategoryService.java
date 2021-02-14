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
import com.vtt.apps.model.SubCategory;
import com.vtt.apps.repository.SubCategoryRepository;

@Service("subCategoryService")
public class SubCategoryService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	SubCategoryRepository subCategoryRepository;

	public Optional<SubCategory> fetchCategoryById(Long id){
		Optional<SubCategory> subCategory =null;
		try {
			subCategory = subCategoryRepository.findById(id);
			if (!subCategory.isPresent())
				throw new CategoryNotFoundException("id-" +subCategory.get().getId());
		}catch(Exception e ) {
			LOGGER.info("Unable to fetch the SubCategory by Id : "+id);
		}
		return subCategory;
	}
	
	public Optional<List<SubCategory>> fetchAll(){
		LOGGER.info("Exec fetchAll ");
		Optional<List<SubCategory>>  categories =null;
		try {
			categories = Optional.of(subCategoryRepository.findAll());
		}catch(Exception e ) {
		}
		return categories;
	}
	
	
	public Optional<SubCategory> update(final SubCategory subCategory) {
//		Optional<SubCategory> foundSubCategory =null;
		return Optional.ofNullable(subCategoryRepository.save(subCategory));
	}

	public ResponseEntity<?> delete(Long id){
		LOGGER.info("Exec delete "+id);
		SubCategory SubCategory =null;
		try {
			SubCategory = subCategoryRepository.findById(id).get();
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the SubCategory by Id : "+id);
			throw new ResourceNotFoundException("SubCategory", "id", id);
		}
		subCategoryRepository.delete(SubCategory);
		return ResponseEntity.ok().build();
	}
	
	public Optional<SubCategory> save(SubCategory subCategory){
		System.err.println("ABOUT to Exec save () SubCategory :--> "+subCategory);
		SubCategory tempCategory =null;
		try{
			tempCategory  = subCategoryRepository.save(subCategory);
			System.out.println("Executed save () SubCategory :--> "+tempCategory);
		}catch (Exception e) {
			throw e;
		}
		return Optional.of(tempCategory);
	}



	public ResponseEntity<?> deleteByName(String name){
		LOGGER.info("Exec deleteByName "+name);
		Optional<SubCategory> subCategory = null;
		try {
			subCategory = subCategoryRepository.findByName(name);
		}catch(Exception e ) {
			LOGGER.info("Unable to Delete the SubCategory by Name : "+name);
			throw new ResourceNotFoundException("SubCategory", " Name", name);
		}
		subCategoryRepository.delete(subCategory.get());
		return ResponseEntity.ok().build();
	}



	public Optional<SubCategory> fetchByName(String name){
		LOGGER.info("Exec fetchByByName "+name);
		Optional<SubCategory> SubCategory=null;
		SubCategory = subCategoryRepository.findByName(name);
		if(SubCategory !=null ) {
			LOGGER.info(" SubCategory :--> "+SubCategory);
			return SubCategory ;
		}else{
			return null;
		}
	}


	/*
	 * public Optional<SubCategory> updatePassword(String name, String newPassword) {
	 * Optional<SubCategory> subCategory =null; Optional<SubCategory> updatedUser =null;
	 * 
	 * try { subCategory = subCategoryRepository.findByUsername(name);
	 * subCategory.get().setPassword(newPassword); updatedUser =
	 * Optional.of(subCategoryRepository.save(subCategory.get())); }catch(Exception e ) {
	 * LOGGER.info("Unable to Update  the userdetai+ls by name: "+name);
	 * throw new ResourceNotFoundException("SubCategory", "id", name); } return
	 * updatedUser; }
	 */
}
