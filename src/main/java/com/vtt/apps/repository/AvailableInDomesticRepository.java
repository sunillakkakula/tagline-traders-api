package com.vtt.apps.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtt.apps.model.AvailableInDomestic;


@Repository
public interface AvailableInDomesticRepository extends JpaRepository<AvailableInDomestic, Long> {
	public Optional<AvailableInDomestic> findById(Long id);
	public Set<AvailableInDomestic> findByProductId(Long productId); 
}