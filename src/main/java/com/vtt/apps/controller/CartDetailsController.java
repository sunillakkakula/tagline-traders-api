package com.vtt.apps.controller;

import static com.vtt.apps.util.ProductToCartItemConverter.convert;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtt.apps.exception.ResourceNotFoundException;
import com.vtt.apps.model.CartDetails;
import com.vtt.apps.model.CartItem;
import com.vtt.apps.model.Product;
import com.vtt.apps.model.UserDetails;
import com.vtt.apps.repository.CartDetailsRepository;
import com.vtt.apps.repository.CartItemRepository;
import com.vtt.apps.repository.UserDetailsRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9090")
public class CartDetailsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	CartDetailsRepository cartDetailsRepository;

	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	/* Add a Product to Cart */
	@PostMapping("/cart/{userId}")
	public CartDetails create(@PathVariable Long userId , @Valid @RequestBody Product product ) {
		CartItem cartItem = null;
		UserDetails userDetails = null;
		CartDetails cartDetails = null;
		List<CartItem> cartItems= null;
		
		/*cartDetailsRepository.save
		1.Convert Product to CartItem
		2.Fetch the User Details
		3.Fetch the CartDetails
		4.Save CART Details with Step 1's CartItem
		*/	
		LOGGER.info("Executing create in CartDetailsController ");
		cartItem = convert(product);
		LOGGER.info("Converted Product-> CartItem : "+cartItem);
		
		CartItem cartItemCreated  = cartItemRepository.save(cartItem);
		
		if(!userDetailsRepository.existsById(userId)) 
			throw new ResourceNotFoundException("User Details ","ID",userId);
		
		userDetails = userDetailsRepository.findById(userId).get();
		cartDetails = userDetails.getCartDetails();
		cartItems = cartDetails .getCartItems();
		cartItems.add(cartItemCreated);
		cartDetails.setCartItems(cartItems);
		return cartDetailsRepository.save(cartDetails);
	}
}


