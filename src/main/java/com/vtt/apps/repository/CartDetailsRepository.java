package com.vtt.apps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vtt.apps.model.CartDetails;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetails, Long>{
	
}