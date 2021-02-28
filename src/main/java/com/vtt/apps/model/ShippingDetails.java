package com.vtt.apps.model;

import java.util.HashSet;
import java.util.Set;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author lskumar
 * @date 2020-02-09
 * @time 12:52
 */

@Entity
@Table(name = "shipping_details") 
@Setter
//@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class ShippingDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "postal_code")
	private String postalCode;
//@JsonIgnore
	@OneToMany(mappedBy="shippingDetails", fetch=FetchType.LAZY, orphanRemoval =
			true,cascade=
		{CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
		}) 
	private Set<OrderDetails> orderDetails;

	public void addOrderDetails(OrderDetails order) {
		if(orderDetails==null)
			orderDetails = new HashSet<>();
		orderDetails.add(order);
		order.setShippingDetails(this); 
	}

	public void removeOrderItem(OrderDetails order) {
		orderDetails.remove(order); 
		order.setShippingDetails(null); 
	}
}
