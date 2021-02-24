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
import com.vtt.apps.model.OrderItem;
import com.vtt.apps.model.ShippingDetails;
import com.vtt.apps.model.UserDetails;
import com.vtt.apps.repository.OrderDetailsRepository;
import com.vtt.apps.repository.OrderItemRepository;
import com.vtt.apps.repository.ShippingDetailsRepository;
import com.vtt.apps.repository.UserDetailsRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090")
public class OrderDetailsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ShippingDetailsRepository shippingDetailsRepository;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;

	/* Get Order By ID */
	@GetMapping("/order/{orderId}")
	public OrderDetails fetchOrderById(@PathVariable Long orderId) {
		LOGGER.info("Executing fetchOrderById in OrderDetailsController");
		if (!orderDetailsRepository.existsById(orderId))
			throw new ResourceNotFoundException("OrderDetails", "ID", orderId);
		return orderDetailsRepository.findById(orderId).get();

	}

	/* Get All Orders */
	@GetMapping("/order")
	public List<OrderDetails> fetchAllOrders() {
		LOGGER.info("Executing fetchAllOrders in OrderDetailsController");
		return orderDetailsRepository.findAll();

	}

	/* Delete Order */
	@DeleteMapping("/order/{orderId}")
	public String delete(@PathVariable Long orderId) {
		if (!orderDetailsRepository.existsById(orderId))
			throw new ResourceNotFoundException("Order", "ID", orderId);
		return orderDetailsRepository.findById(orderId).map(order -> {
			orderDetailsRepository.delete(order);
			return "Deleted Successfully!";
		}).orElseThrow(() -> new ResourceNotFoundException("Order ", "Id ", orderId));
	}

	/* Create a new Order */
	@PostMapping("/order/{userId}")
	public OrderDetails create(@Valid @RequestBody OrderDetails orderDetails, @PathVariable Long userId) {
		LOGGER.info("Executing create in ProductController create : " + orderDetails);
		System.err.println("Executing create in ProductController create : " + orderDetails.getOrderItems());

		if (!userDetailsRepository.existsById(userId))
			throw new ResourceNotFoundException("User ", "ID", userId);
		UserDetails user = userDetailsRepository.findById(userId).get();
//		 orderDetails.setOrderItems(orderDetails.getOrderItems());

		/*
		 * return categoryRepository.findById(catId) .map(category -> {
		 * subCategory.setCategory(category); return
		 * subCategoryRepository.save(subCategory); }).orElseThrow(() -> new
		 * ResourceNotFoundException("Category ","catId ",catId));
		 */
		OrderDetails newOrder = new OrderDetails();
		newOrder.setOrderCost(orderDetails.getOrderCost());
		newOrder.setDeliveryDate(orderDetails.getDeliveryDate());
		newOrder.setOrderStatus(orderDetails.getOrderStatus());
		newOrder.setPaymentStatus(orderDetails.getPaymentStatus());
		newOrder.setPaymentType(orderDetails.getPaymentType());
		newOrder.setUserId(userId);
		ShippingDetails newShippingDetails = shippingDetailsRepository.save(orderDetails.getShippingDetails());
		newOrder.setShippingDetails(newShippingDetails);
		OrderDetails createdOrder = orderDetailsRepository.save(newOrder);
		
		
		for(OrderItem orderItem :orderDetails.getOrderItems()) {
			createdOrder.addOrderItem(orderItem);
//			orderItemRepository.save(orderItem); 
			System.err.println("Adding Order Item : "+orderItem);
		}
		orderDetailsRepository.save(createdOrder);
		
		/*
		 * for(OrderItem orderItem :orderDetails.getOrderItems()) {
		 * System.err.println("Adding Order Item : "+orderItem);
		 * orderDetails.addOrderItem(orderItem ); }
		 * 
		 * OrderDetails newOrderDetails = new OrderDetails(); for(OrderItem orderItem
		 * :orderDetails.getOrderItems()) {
		 * System.err.println("Adding Order Item : "+orderItem);
		 * newOrderDetails.addOrderItem(orderItem ); }
		 */

//		OrderDetails createdOrder = orderDetailsRepository.save(orderDetails);
//		userDetailsRepository.save(user);
		return createdOrder;
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
