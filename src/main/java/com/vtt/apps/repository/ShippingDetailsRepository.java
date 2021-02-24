package com.vtt.apps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtt.apps.model.ShippingDetails;


@Repository
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, Long> {
	public Optional<ShippingDetails> findById(Long id);
}