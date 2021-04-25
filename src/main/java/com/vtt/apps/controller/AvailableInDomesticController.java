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
import com.vtt.apps.model.AvailableInDomestic;
import com.vtt.apps.repository.AvailableInDomesticRepository;
import com.vtt.apps.repository.ProductRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AvailableInDomesticController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	AvailableInDomesticRepository availableInDomesticRepository;

	@Autowired
	ProductRepository productRepository;

	// Get All AvailableInDomestic by Product ID
	@GetMapping("/product/{productId}/avail-domestic")
	public Set<AvailableInDomestic> fetchAvailableInBulkByProductId(@PathVariable Long productId) {
		LOGGER.info("Executing fetchAvailableInBulkByProductId in AvailableInBulkController");
		if(!productRepository.existsById(productId)) 
			throw new ResourceNotFoundException("Product ","ID",productId);
		return availableInDomesticRepository.findByProductId(productId);
	}

	/*
	 *  Create a new AbailableInBulk
	 */		@PostMapping("/product/{productId}/avail-domestic")
	 public AvailableInDomestic create(@PathVariable Long productId,@Valid @RequestBody AvailableInDomestic availableInDomestic) {
		 LOGGER.info("Executing create in AvailableInBulkController product : "+availableInDomestic );
		 return productRepository.findById(productId)
				 .map(product -> {
					 availableInDomestic.setProduct(product);
					 return availableInDomesticRepository.save(availableInDomestic);
				 }).orElseThrow(() -> new ResourceNotFoundException("AVailableInBulk","productId ",productId));
	 }

	 @PutMapping("/product/{productId}/avail-domestic/{availableindomId}")
	 public AvailableInDomestic update(@PathVariable Long productId,
			 @PathVariable Long availableindomId,
			 @Valid @RequestBody AvailableInDomestic updtAvailableInBulk) {

		 if(!productRepository.existsById(productId)) {
			 throw new ResourceNotFoundException("Product ","prodId" ,productId);
		 }

		 return availableInDomesticRepository.findById(availableindomId)
				 .map(availInBulk-> {
					 availInBulk.setUnitOfMessure(updtAvailableInBulk.getUnitOfMessure());
					 availInBulk.setUnitPrice(updtAvailableInBulk.getUnitPrice());
					 availInBulk.setSellingPrice(updtAvailableInBulk.getSellingPrice());
					 availInBulk.setQty(updtAvailableInBulk.getQty());
					 availInBulk.setMrp(updtAvailableInBulk.getMrp());
					 return availableInDomesticRepository.save(availInBulk);
				 }).orElseThrow(() -> new ResourceNotFoundException("AvailableInDomestic","ID ",availableindomId));
	 }

	 @DeleteMapping("/product/{productId}/avail-domestic/{availableindomId}")
	 public String delete(@PathVariable Long productId,
			 @PathVariable Long availableindomId) {

		 if(!productRepository.existsById(productId)) 
			 throw new ResourceNotFoundException("Prodct","",productId);

		 return availableInDomesticRepository.findById(availableindomId)
				 .map(availableInDomestic -> {
					 availableInDomesticRepository.delete(availableInDomestic);
					 return "Deleted Successfully!";
				 }).orElseThrow(() -> new ResourceNotFoundException("AvailableInDomestic","Id ",availableindomId));
	 }


}


