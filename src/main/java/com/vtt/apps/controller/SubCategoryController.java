package com.vtt.apps.controller;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vtt.apps.exception.CategoryNotFoundException;
import com.vtt.apps.exception.ResourceNotFoundException;
import com.vtt.apps.model.SubCategory;
import com.vtt.apps.repository.CategoryRepository;
import com.vtt.apps.repository.SubCategoryRepository;
import com.vtt.apps.service.SubCategoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SubCategoryController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	SubCategoryService subCategoryService;
	
	@Autowired
	SubCategoryRepository subCategoryRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	// Get All Categories
	@GetMapping("/category/{catId}/subcategories")
	public Set<SubCategory> fetchSubCategoriesByCategoryId(@PathVariable Long catId) {
		LOGGER.info("Executing retrieveAll in SubCategoryController");
		 if(!categoryRepository.existsById(catId)) 
	            throw new ResourceNotFoundException("Category not found!","",catId);
		 return subCategoryRepository.findByCategoryId(catId);
		
	}

	// Create a new subcategory
	@PostMapping("/category/{catId}/subcategory")
	public SubCategory create(@PathVariable Long catId,@Valid @RequestBody SubCategory subCategory ) {
		LOGGER.info("Executing create in SubCategoryController subcategory : "+subCategory );

		return categoryRepository.findById(catId)
                .map(category -> {
                	subCategory.setCategory(category);
                    return subCategoryRepository.save(subCategory);
                }).orElseThrow(() -> new ResourceNotFoundException("Category ","catId ",catId));
	}

	 @PutMapping("/category/{catId}/subcategory/{subCatId}")
	    public SubCategory update(@PathVariable Long catId,
	    								@PathVariable Long subCatId,
	    								@Valid @RequestBody SubCategory updatedSubCategory) {
	    	
	    	if(!categoryRepository.existsById(catId)) {
	    		throw new ResourceNotFoundException("Category not found!","" ,catId);
	    	}
	    	
	        return subCategoryRepository.findById(subCatId)
	                .map(subCategory -> {
	                	subCategory.setName(updatedSubCategory.getName());
	                	subCategory.setImageurl(updatedSubCategory.getImageurl());
	                    return subCategoryRepository.save(subCategory);
	                }).orElseThrow(() -> new ResourceNotFoundException("Sub Category ","ID ",subCatId));
	    }
	
	 @DeleteMapping("/category/{catId}/subcategory/{subCatId}")
	    public String delete(@PathVariable Long catId,
	    							   @PathVariable Long subCatId) {
	    	
	    	if(!categoryRepository.existsById(catId)) {
	    		throw new ResourceNotFoundException("Category","",catId);
	    	}
	    	
	        return subCategoryRepository.findById(subCatId)
	                .map(subCategory -> {
	                	subCategoryRepository.delete(subCategory);
	                    return "Deleted Successfully!";
	                }).orElseThrow(() -> new ResourceNotFoundException("Sub Category ","Id ",subCatId));
	    }
	 
		/*
		 * // Get a Single subcategory by ID
		 * 
		 * @GetMapping("/subcategory/{id}") public Optional<SubCategory>
		 * getById(@PathVariable(value = "id") Long categoryId) {
		 * LOGGER.info("Executing getById in SubCategoryController");
		 * Optional<SubCategory> subcategory =
		 * subCategoryService.fetchCategoryById(categoryId); if
		 * (!subcategory.isPresent()) throw new CategoryNotFoundException("id-"
		 * +categoryId); return subcategory ; }
		 */


	// Update a subcategory
	/*
	 * @PutMapping("/subcategory/{id}") public Optional<SubCategory>
	 * update(@PathVariable Long id,
	 * 
	 * @Valid @RequestBody SubCategory catUpdt) {
	 * LOGGER.info("Executing update in SubCategoryController");
	 * System.err.println("catUpdt : "+catUpdt); Optional<SubCategory> subcategory =
	 * subCategoryService.fetchCategoryById(id); if (!subcategory.isPresent()) throw
	 * new CategoryNotFoundException("id-" +id); SubCategory foundCategory =
	 * subcategory.get(); foundCategory.setName(catUpdt.getName());
	 * foundCategory.setImageurl(catUpdt.getImageurl()); return
	 * subCategoryService.update(foundCategory); }
	 */
	// Delete a subcategory
	/*
	 * @DeleteMapping("/subcategory/{id}") public String delete(@PathVariable(value
	 * = "id") Long id) {
	 * LOGGER.info("Executing /subcategory/{id} in SubCategoryController"); return
	 * subCategoryRepository.findById(id).map(subcat -> {
	 * subCategoryRepository.delete(subcat); return "Delete Successfully!";
	 * }).orElseThrow(() -> new ResourceNotFoundException("SubCategory","id" , id));
	 * }
	 */
}


