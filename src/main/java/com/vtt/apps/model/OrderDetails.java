package com.vtt.apps.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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

@Entity
@Table(name = "order_details")
@EntityListeners(AuditingEntityListener.class)
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
	@Column(name="order_cost")
	private float orderCost;
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
	
	@Column(name="consumer_id")
	private Long consumerId;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE},mappedBy="orderDetails")
	private List<OrderItem> orderItems;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 * 
	 * @JoinColumn(name = "user_details_id", nullable = false)
	 * 
	 * @JsonIgnore private UserDetails uerDetails;
	 */

}