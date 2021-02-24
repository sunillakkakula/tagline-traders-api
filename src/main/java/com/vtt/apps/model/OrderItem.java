package com.vtt.apps.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
/**
 * 
 * @author slakkakula
 *
 */
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="unit_cost")
	private Integer unitCost;
	@Column(name="quantity_ordered")
	private Integer quantityOrdered;
	@Column(name="total_cost")
	private Float totalCost;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY) private OrderDetails orderDetails;
	 */
	 @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private OrderDetails orderDetails;

}