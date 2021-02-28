package com.vtt.apps.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 
 * @author slakkakula
 *
 */
public class OrderDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="items_price")
	private Float itemsPrice;

	@Column(name="tax_price")
	private Float taxPrice;
	
	@Column(name="shipping_price")
	private Float shippingPrice;
	
	@Column(name="total_price")
	private Float totalPrice;
	
	@Column(name="payment_type")
	private String paymentType;

	@Column(name="payment_status")
	private String paymentStatus;

	@Column(name="order_status")
	private String orderStatus;

	@Column(name="order_date")
	private Date orderDate;

	@Column(name="delivery_date")
	private Date deliveryDate;

	@Column(name="is_active")
	private Boolean isActive;


//	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private ShippingDetails shippingDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private PaymentDetails paymentDetails;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY, optional = true)
	 * 
	 * @JoinColumn(name = "shipping_details_id", nullable = true)
	 * 
	 * @JsonIgnore private ShippingDetails shippingDetails ;
	 */
	@Column(name="user_id")
	private Long userId;
	
	@OneToMany(mappedBy="orderDetails",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<OrderItem> orderItems;

	public void addOrderItem(OrderItem orderItem) {
		if(orderItems==null)
			orderItems = new HashSet<>();
		orderItems.add(orderItem);
		orderItem.setOrderDetails(this); 
	}

	public void removeOrderItem(OrderItem orderItem) {
		orderItems.remove(orderItem); orderItem.setOrderDetails(null); 
	}
}