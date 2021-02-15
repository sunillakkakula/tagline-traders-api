package com.vtt.apps.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_details") // the name user can be ambiguous for postgresql main tables.
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 
 * @author slakkakula
 *
 */
public class CartDetails implements Serializable {
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
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,
			cascade= {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE},mappedBy="cartDetails")
	private List<CartItem> cartItems;

}
