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
import com.vtt.apps.model.AvailableInBulk;
import com.vtt.apps.model.SubCategory;
import com.vtt.apps.repository.AvailableInBulkRepository;
import com.vtt.apps.repository.ProductRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090")
public class AvailableInBulkController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	AvailableInBulkRepository availableInBulkRepository;

	@Autowired
	ProductRepository productRepository;

	// Get All AvailableInBulk by Product ID
	@GetMapping("/product/{productId}/availableinbulk")
	public Set<AvailableInBulk> fetchAvailableInBulkByProductId(@PathVariable Long productId) {
		LOGGER.info("Executing fetchAvailableInBulkByProductId in AvailableInBulkController");
		if(!productRepository.existsById(productId)) 
			throw new ResourceNotFoundException("Product ","ID",productId);
		return availableInBulkRepository.findByProductId(productId);
	}

	/*
	 *  Create a new AbailableInBulk
	 */		@PostMapping("/product/{productId}/availableinbulk")
	 public AvailableInBulk create(@PathVariable Long productId,@Valid @RequestBody AvailableInBulk availableInBulk) {
		 LOGGER.info("Executing create in AvailableInBulkController product : "+availableInBulk );
		 return productRepository.findById(productId)
				 .map(product -> {
					 availableInBulk.setProduct(product);
					 return availableInBulkRepository.save(availableInBulk);
				 }).orElseThrow(() -> new ResourceNotFoundException("AVailableInBulk","productId ",productId));
	 }

	 @PutMapping("/product/{productId}/availableinbulk/{availableinbulkId}")
	 public AvailableInBulk update(@PathVariable Long productId,
			 @PathVariable Long availableinbulkId,
			 @Valid @RequestBody AvailableInBulk updtAvailableInBulk) {

		 if(!productRepository.existsById(productId)) {
			 throw new ResourceNotFoundException("Product ","prodId" ,productId);
		 }

		 return availableInBulkRepository.findById(availableinbulkId)
				 .map(availInBulk-> {
					 availInBulk.setUnitOfMessure(updtAvailableInBulk.getUnitOfMessure());
					 availInBulk.setUnitPrice(updtAvailableInBulk.getUnitPrice());
					 availInBulk.setSellingPrice(updtAvailableInBulk.getSellingPrice());
					 availInBulk.setQty(updtAvailableInBulk.getQty());
					 availInBulk.setMrp(updtAvailableInBulk.getMrp());
					 return availableInBulkRepository.save(availInBulk);
				 }).orElseThrow(() -> new ResourceNotFoundException("AvailableInBulk","ID ",availableinbulkId));
	 }

	 @DeleteMapping("/product/{productId}/availableinbulk/{availableinbulkId}")
	 public String delete(@PathVariable Long productId,
			 @PathVariable Long availableinbulkId) {

		 if(!productRepository.existsById(productId)) 
			 throw new ResourceNotFoundException("Prodct","",productId);

		 return availableInBulkRepository.findById(availableinbulkId)
				 .map(availableInBulk -> {
					 availableInBulkRepository.delete(availableInBulk);
					 return "Deleted Successfully!";
				 }).orElseThrow(() -> new ResourceNotFoundException("AvailableInBulk","Id ",availableinbulkId));
	 }


}


