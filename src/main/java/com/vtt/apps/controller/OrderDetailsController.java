package com.vtt.apps.controller;

import java.time.LocalDateTime;
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
import com.vtt.apps.model.PaymentDetails;
import com.vtt.apps.model.ShippingDetails;
import com.vtt.apps.model.UserDetails;
import com.vtt.apps.repository.OrderDetailsRepository;
import com.vtt.apps.repository.OrderItemRepository;
import com.vtt.apps.repository.PaymentDetailsRepository;
import com.vtt.apps.repository.ShippingDetailsRepository;
import com.vtt.apps.repository.UserDetailsRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderDetailsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	OrderDetailsRepository orderDetailsRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	ShippingDetailsRepository shippingDetailsRepository;

	@Autowired
	PaymentDetailsRepository paymentDetailsRepository;

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
		OrderDetails newOrder = new OrderDetails();
		newOrder.setItemsPrice(orderDetails.getItemsPrice());
		newOrder.setTaxPrice(orderDetails.getTaxPrice());
		newOrder.setShippingPrice(orderDetails.getShippingPrice());
		newOrder.setTotalPrice(orderDetails.getTotalPrice());
		
		newOrder.setDeliveryDate(orderDetails.getDeliveryDate());
		newOrder.setOrderStatus(orderDetails.getOrderStatus());
		newOrder.setPaymentStatus(orderDetails.getPaymentStatus());
		newOrder.setPaymentType(orderDetails.getPaymentType());
		newOrder.setUserId(userId);
		ShippingDetails newShippingDetails = shippingDetailsRepository.save(orderDetails.getShippingDetails());
		PaymentDetails payDetails = orderDetails.getPaymentDetails();
		payDetails.setPaymentDateTime(LocalDateTime.now());
		payDetails.setPaymentDate(LocalDateTime.now().toLocalDate());
		payDetails.setPaymentTime(LocalDateTime.now().toLocalTime());

		PaymentDetails newPaymentDetails = paymentDetailsRepository.save(orderDetails.getPaymentDetails());
		newOrder.setShippingDetails(newShippingDetails);
		newOrder.setPaymentDetails(newPaymentDetails);
		OrderDetails createdOrder = orderDetailsRepository.save(newOrder);

		if(orderDetails.getOrderItems()!=null && orderDetails.getOrderItems().size()>0)
			for(OrderItem orderItem :orderDetails.getOrderItems()) {
				System.err.println("orderItem :-> "+orderItem);
				createdOrder.addOrderItem(orderItem);
			}
		orderDetailsRepository.save(createdOrder);

		return createdOrder;
	}


}
