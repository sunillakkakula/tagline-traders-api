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
import com.vtt.apps.model.ShippingDetails;
import com.vtt.apps.repository.ShippingDetailsRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090")
public class ShippingDetailsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	ShippingDetailsRepository shippingDetailsRepository;


	// Get All Categories
	@GetMapping("/shipping-details")
	public List<ShippingDetails> retrieveAll() {
		LOGGER.info("Executing retrieveAll in ShippingDetailsController");
		return shippingDetailsRepository.findAll();
	}

	@GetMapping("/shipping-details/{id}")
	public ShippingDetails fetchById(@PathVariable Long id) {
		LOGGER.info("Executing FETCHBY ID /shipping-details/{id} in ShippingDetailsController");
		Optional<ShippingDetails> result = shippingDetailsRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new ResourceNotFoundException("ShippingDetails " ,"ID", id);
		}
	}

	@PostMapping("/shipping-details")
	public ShippingDetails create(@Valid @RequestBody ShippingDetails ShippingDetails) {
		LOGGER.info("Executing CREATE  in ShippingDetailsController ShippingDetails : "+ShippingDetails );
		return shippingDetailsRepository.save(ShippingDetails);
	}

	@PutMapping("/shipping-details/{id}")
	public ShippingDetails update(@PathVariable Long id,
			@Valid @RequestBody ShippingDetails shippingDetailsUpdated) {
		LOGGER.info("Executing UPDATE /shipping-details/{id} in ShippingDetailsController");
		return shippingDetailsRepository.findById(id)
				.map(shippingDetails -> {
					shippingDetails.setAddress(shippingDetailsUpdated.getAddress());
					shippingDetails.setCity(shippingDetailsUpdated.getCity());
					shippingDetails.setPostalCode(shippingDetailsUpdated.getPostalCode());
					return shippingDetailsRepository.save(shippingDetails);
				}).orElseThrow(() -> new ResourceNotFoundException("ShippingDetails","ID" , id));
	}

	@DeleteMapping("/shipping-details/{id}")
	public String delete(@PathVariable Long id) {
		LOGGER.info("Executing DELETE /shipping-details/{id} in ShippingDetailsController");
		return shippingDetailsRepository.findById(id)
				.map(shippingDetails -> {
					shippingDetailsRepository.delete(shippingDetails);
					return "Delete Successfully!";
				}).orElseThrow(() -> new ResourceNotFoundException("ShippingDetails","ID" , id));
	}

}


