package com.vtt.apps.controller;

import static com.vtt.apps.util.ProductToCartItemConverter.convert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtt.apps.exception.ResourceNotFoundException;
import com.vtt.apps.model.CartDetails;
import com.vtt.apps.model.CartItem;
import com.vtt.apps.model.Product;
import com.vtt.apps.model.UserDetails;
import com.vtt.apps.repository.CartDetailsRepository;
import com.vtt.apps.repository.CartItemRepository;
import com.vtt.apps.repository.ProductRepository;
import com.vtt.apps.repository.UserDetailsRepository;;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CartDetailsController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	CartDetailsRepository cartDetailsRepository;

	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	/* Fetch All Cart Items By USer ID */ 
	@GetMapping("/cart-items/{userId}")
	public Set<CartItem> fetchAllCartItems(@PathVariable Long userId ) {
		LOGGER.info("Executing getchAllCartItems in CartDetailsController ");
		UserDetails userDetails = null;
		CartDetails cartDetails = null;
		if(!userDetailsRepository.existsById(userId)) 
			throw new ResourceNotFoundException("User Details ","ID",userId);
		userDetails = userDetailsRepository.findById(userId).get();
		cartDetails = userDetails.getCartDetails();
		
		return cartDetails .getCartItems();
	}
	
	/* Delete All Cart Items By USer ID */
	@DeleteMapping("/cart-items/{userId}/remove-all")
	public void deleteAllCartItems(@PathVariable Long userId ) {
		LOGGER.info("Executing deleteAllCartItems in CartDetailsController ");
		if(!userDetailsRepository.existsById(userId)) 
			throw new ResourceNotFoundException("User Details ","ID",userId);
		cartItemRepository.deleteAll();
	}
	
	/* Delete Cart Items By USer ID and Item ID*/
	@DeleteMapping("/cart-items/{userId}/remove-item/{itemId}")
	public String deleteCartItem(@PathVariable Long userId , @PathVariable Long itemId ) {
		LOGGER.info("Executing deleteCartItem in CartDetailsController ");
		if(!userDetailsRepository.existsById(userId)) 
			throw new ResourceNotFoundException("User Details ","ID",userId);
		return cartItemRepository.findById(itemId) .map(cartItem -> {
			cartItemRepository.delete(cartItem);
			System.err.println("SUCCESSFULLY DELETED A CART ITEM "+itemId+" , Find All Remaining:--> "+cartItemRepository.findAll());
			return "Deleted Successfully!";
		}).orElseThrow(() -> new
				ResourceNotFoundException("Cart Item ","Id ",itemId)); }

	
	/* Update Cart Item from the Cart */
	@PutMapping("/cart-items/{userId}/update-item/{itemId}") 
	public CartItem update(@PathVariable Long userId,@PathVariable Long itemId,@RequestParam String qty,@RequestParam String orderType,@RequestParam String uomSelected) {
		LOGGER.info("Executing update in CartDetailsController ");
		if(!userDetailsRepository.existsById(userId)) 
			throw new ResourceNotFoundException("User Details ","ID",userId);
		if(!cartItemRepository.existsById(itemId)) { throw new
			ResourceNotFoundException("CartItem ","ID" ,itemId); }

		return cartItemRepository.findById(itemId) .map(cartItem -> {
			cartItem.setOrderType(orderType);
			cartItem.setQty(Integer.valueOf(qty));
			cartItem.setSelectedUom(uomSelected);
			
			return cartItemRepository.save(cartItem); })
				.orElseThrow(() -> new ResourceNotFoundException("CartItem","itemID ",itemId)); 
		}
	
	
	/* Add a Product to Cart */
	@PostMapping("/cart/{userId}/{productId}")
	public CartDetails create(@PathVariable Long userId ,@PathVariable Long productId ,@RequestParam String qty,@RequestParam String orderType,@RequestParam String uomSelected) {
		CartItem cartItem = null;
		UserDetails userDetails = null;
		CartDetails cartDetails = null;
		Set<CartItem> cartItems= null;
		Product product = null;
	
		/*cartDetailsRepository.save
		1.Convert Product to CartItem
		2.Fetch the User Details
		3.Fetch the CartDetails
		4.Save CART Details with Step 1's CartItem
		*/
		
		LOGGER.info("Executing create in CartDetailsController ");
		System.err.println("orderType:"+orderType+" , selectedUom:"+uomSelected+" ,qty : "+qty);
		
		if(!userDetailsRepository.existsById(userId)) 
			throw new ResourceNotFoundException("User Details ","ID",userId);
		
		if(!productRepository.existsById(productId)) 
			throw new ResourceNotFoundException("Product ","ID",userId);
		product = productRepository.findById(productId).get();
		userDetails = userDetailsRepository.findById(userId).get();
		cartDetails = userDetails.getCartDetails();
		cartItem = convert(product,qty,orderType,uomSelected);
		LOGGER.info("Converted Product-> CartItem : "+cartItem);
		cartItem.setCartDetails(cartDetails);
		CartItem cartItemCreated  = cartItemRepository.save(cartItem);
		
		if(cartDetails .getCartItems()==null) 
			cartItems = new HashSet<CartItem>();
		else
			cartItems = cartDetails .getCartItems();
		cartItems.add(cartItemCreated);
		cartDetails.setCartItems(cartItems);
		return cartDetailsRepository.save(cartDetails);
	}
	
}


