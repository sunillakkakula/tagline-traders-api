package com.vtt.apps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtt.apps.model.OrderDetails_BKP;


@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails_BKP, Long> {
	public Optional<OrderDetails_BKP> findById(Long id);
 
}