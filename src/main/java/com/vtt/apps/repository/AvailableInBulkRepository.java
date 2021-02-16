package com.vtt.apps.repository;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vtt.apps.model.AvailableInBulk;


@Repository
public interface AvailableInBulkRepository extends JpaRepository<AvailableInBulk, Long> {
	public Optional<AvailableInBulk> findById(Long id);
	public Set<AvailableInBulk> findByProductId(Long productId); 
}