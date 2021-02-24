package com.vtt.apps.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtt.apps.exception.ResourceNotFoundException;
import com.vtt.apps.model.Category;
import com.vtt.apps.repository.CategoryRepository;
import com.vtt.apps.service.CategoryService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090")
public class CategoryController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	CategoryService categoryService;

	@Autowired
	CategoryRepository categoryRepository;


	// Get All Categories
	@GetMapping("/category")
	public List<Category> retrieveAll() {
		LOGGER.info("Executing retrieveAll in CategoryController");
		return categoryRepository.findAll();
	}

	@GetMapping("/category/{id}")
	public Category fetchById(@PathVariable Long id) {
		LOGGER.info("Executing FETCHBY ID /category/{id} in CategoryController");
		Optional<Category> result = categoryRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new ResourceNotFoundException("Category " ,"ID", id);
		}
	}

	@PostMapping("/category")
	public Category create(@Valid @RequestBody Category category) {
		LOGGER.info("Executing CREATE  in CategoryController category : "+category );
		return categoryRepository.save(category);
	}

	@PutMapping("/category/{id}")
	public Category update(@PathVariable Long id,
			@Valid @RequestBody Category categoryUpdated) {
		LOGGER.info("Executing UPDATE /category/{id} in CategoryController");
		return categoryRepository.findById(id)
				.map(category -> {
					category.setName(categoryUpdated.getName());
					category.setImageurl(categoryUpdated.getImageurl());
					return categoryRepository.save(category);
				}).orElseThrow(() -> new ResourceNotFoundException("Category","ID" , id));
	}

	@DeleteMapping("/students/{id}")
	public String delete(@PathVariable Long id) {
		LOGGER.info("Executing DELETE /category/{id} in CategoryController");
		return categoryRepository.findById(id)
				.map(category -> {
					categoryRepository.delete(category);
					return "Delete Successfully!";
				}).orElseThrow(() -> new ResourceNotFoundException("Category","ID" , id));
	}

	/*
	 * // Create a new category
	 * 
	 * @PostMapping("/category") public ResponseEntity<Object>
	 * create(@Valid @RequestBody Category category ) {
	 * LOGGER.info("Executing create in CategoryController category : "+category );
	 * 
	 * 
	 * LOGGER.info("category : "+category);
	 * System.err.println("******* category *********: "+category);
	 * Optional<Category> createdCategory = categoryService.save(category);
	 * 
	 * URI location = ServletUriComponentsBuilder .fromCurrentRequest()
	 * .path("/{id}") .buildAndExpand(createdCategory.get().getId()).toUri(); return
	 * ResponseEntity.created(location).build(); }
	 */


	// Get a Single category by ID
	/*
	 * @GetMapping("/category/{id}") public Optional<Category>
	 * getById(@PathVariable(value = "id") Long categoryId) {
	 * LOGGER.info("Executing getById in CategoryController"); Optional<Category>
	 * category = categoryService.fetchCategoryById(categoryId); if
	 * (!category.isPresent()) throw new CategoryNotFoundException("id-"
	 * +categoryId); return category ; }
	 */

	/*
	 * // Update a category
	 * 
	 * @PutMapping("/category/{id}") public Category update(@PathVariable Long id,
	 * 
	 * @Valid @RequestBody Category catUpdt) {
	 * LOGGER.info("Executing update in CategoryController");
	 * System.err.println("catUpdt : "+catUpdt); Optional<Category> category =
	 * categoryService.fetchCategoryById(id); return categoryRepository.findById(id)
	 * .map(foundCatg -> { foundCatg.setName(catUpdt.getName());
	 * foundCatg.setImageurl(catUpdt.getImageurl()); return
	 * categoryRepository.save(foundCatg); }).orElseThrow(() -> new
	 * ResourceNotFoundException("Category ","id" ,id)); }
	 */

	/*
	 * // Delete a category
	 * 
	 * @DeleteMapping("/category/{id}") public ResponseEntity<?>
	 * delete(@PathVariable(value = "id") Long id) {
	 * LOGGER.info("Executing /category/{id} in CategoryController"); return
	 * categoryService.delete(id); }
	 */


}


