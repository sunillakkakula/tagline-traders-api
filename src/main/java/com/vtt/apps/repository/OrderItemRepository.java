package com.vtt.apps.repository;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vtt.apps.model.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	public Optional<OrderItem> findById(Long id);
	Set<OrderItem> findByOrderDetailsId(Long orderId); 
}