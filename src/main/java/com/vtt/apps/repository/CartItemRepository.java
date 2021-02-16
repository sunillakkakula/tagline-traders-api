package com.vtt.apps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtt.apps.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
}