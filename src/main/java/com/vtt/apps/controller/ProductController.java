package com.vtt.apps.controller;

import java.util.Set;

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
import com.vtt.apps.model.Product;
import com.vtt.apps.repository.ProductRepository;
import com.vtt.apps.repository.SubCategoryRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	ProductRepository productRepository;

	@Autowired
	SubCategoryRepository subCategoryRepository;

	/* Get All Products by Sub Category */
	@GetMapping("/subcategory/{subCatId}/product")
	public Set<Product> fetchProductsBySubCategoryId(@PathVariable Long subCatId) {
		LOGGER.info("Executing fetchProductsBySubCategoryId in ProductController");
		if(!subCategoryRepository.existsById(subCatId)) 
			throw new ResourceNotFoundException("SubCategory","ID",subCatId);
		return productRepository.findBySubCategoryId(subCatId);

	}
	
	/* Get Product by Sub Category ID and Product ID */
	@GetMapping("/subcategory/{subCatId}/product/{productId}")
	public Product fetchProductBySubCategoryIdAndProductId(@PathVariable Long subCatId, @PathVariable Long productId) {
		LOGGER.info("Executing fetchProductBySubCategoryIdAndProductId in ProductController");
		if(!subCategoryRepository.existsById(subCatId)) 
			throw new ResourceNotFoundException("SubCategory","ID",subCatId);
		if(!productRepository.existsById(productId)) 
			throw new ResourceNotFoundException("Product","ID",productId);
		return productRepository.findById(productId).get();
	}
	
	/* Create a new Product */
	@PostMapping("/subcategory/{subCatId}/product")
	public Product create(@PathVariable Long subCatId,@Valid @RequestBody Product product ) {
		LOGGER.info("Executing create in ProductController subcategory : "+product );

		return subCategoryRepository.findById(subCatId)
				.map(subCategory -> {
					product.setSubCategory(subCategory);
					return productRepository.save(product);
				}).orElseThrow(() -> new ResourceNotFoundException("SubCategory ","subCatId ",subCatId));
	}


	@PutMapping("/subcategory/{subCatId}/product/{productId}") 
	public Product update(@PathVariable Long subCatId,@PathVariable Long productId,@Valid @RequestBody Product updatedProduct) {

		if(!subCategoryRepository.existsById(subCatId)) { throw new
			ResourceNotFoundException("Sub Category ","ID" ,subCatId); }

		return productRepository.findById(productId) .map(product -> {
			product.setName(updatedProduct.getName());
			product.setImageurl(updatedProduct.getImageurl());
			product.setBrand(updatedProduct.getBrand());
			product.setDescription(updatedProduct.getDescription()); 
			product.setCountInStock(updatedProduct.getCountInStock());
			product.setIsTaxable(updatedProduct.getIsTaxable()); 
			product.setTaxPercent(updatedProduct.getTaxPercent());
			
			return productRepository.save(product); })
				.orElseThrow(() -> new ResourceNotFoundException("Product","productID ",productId)); 
		}

	@DeleteMapping("/subcategory/{subCatId}/product/{productId}") 
	public String delete(@PathVariable Long subCatId,@PathVariable Long productId) {

		if(!subCategoryRepository.existsById(subCatId)) { throw new
			ResourceNotFoundException("SubCategory","ID",subCatId); }

		return productRepository.findById(productId) .map(product -> {
			productRepository.delete(product); return "Deleted Successfully!";
		}).orElseThrow(() -> new
				ResourceNotFoundException("Product ","Id ",productId)); }


}


