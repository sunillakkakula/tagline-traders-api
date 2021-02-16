package com.vtt.apps.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtt.apps.exception.ResourceNotFoundException;
import com.vtt.apps.model.OrderDetails;
import com.vtt.apps.repository.OrderDetailsRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090")
public class OrderDetailsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	/* Get Order By ID */
	@GetMapping("/order/{orderId}")
	public OrderDetails fetchOrderById(@PathVariable Long orderId) {
		LOGGER.info("Executing fetchOrderById in OrderDetailsController");
		if (!orderDetailsRepository.existsById(orderId))
			throw new ResourceNotFoundException("OrderDetails", "ID", orderId);
		return orderDetailsRepository.findById(orderId).get();

	}

	/* Get All Orders*/
	@GetMapping("/order")
	public List<OrderDetails> fetchAllOrders() {
		LOGGER.info("Executing fetchAllOrders in OrderDetailsController");
		return orderDetailsRepository.findAll();

	}

	/* Delete Order */
	@DeleteMapping("/order/{orderId}") 
	public String delete(@PathVariable Long orderId) {
		if(!orderDetailsRepository.existsById(orderId))  
			throw new ResourceNotFoundException("Order","ID",orderId); 
		return orderDetailsRepository.findById(orderId) .map(order -> {
			orderDetailsRepository.delete(order); return "Deleted Successfully!";
		}).orElseThrow(() -> new
				ResourceNotFoundException("Order ","Id ",orderId)); }

	/* Create a new Order */
	@PostMapping("/order") 
	public OrderDetails create(@Valid @RequestBody OrderDetails orderDetails) {
		LOGGER.info("Executing create in ProductController create : "+orderDetails );
		return orderDetailsRepository.save(orderDetails); 
	}


	
	/*
	 * @PutMapping("/subcategory/{subCatId}/product/{productId}") public Product
	 * update(@PathVariable Long subCatId,@PathVariable Long
	 * productId,@Valid @RequestBody Product updatedProduct) {
	 * 
	 * if(!subCategoryRepository.existsById(subCatId)) { throw new
	 * ResourceNotFoundException("Sub Category ","ID" ,subCatId); }
	 * 
	 * return orderDetailsRepository.findById(productId) .map(product -> {
	 * product.setName(updatedProduct.getName());
	 * product.setImageurl(updatedProduct.getImageurl());
	 * product.setBrand(updatedProduct.getBrand());
	 * product.setDescription(updatedProduct.getDescription());
	 * product.setCountInStock(updatedProduct.getCountInStock());
	 * product.setIsTaxable(updatedProduct.getIsTaxable());
	 * product.setTaxPercent(updatedProduct.getTaxPercent());
	 * 
	 * return orderDetailsRepository.save(product); }) .orElseThrow(() -> new
	 * ResourceNotFoundException("Product","productID ",productId)); }
	 */ 


}
