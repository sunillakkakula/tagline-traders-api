package com.vtt.apps.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
@Table(name = "payment_details") 
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class PaymentDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "status")
	private String status;
	
	/*@Column(name = "payment_date")
	private LocalDateTime paymentDate;*/
	
	
	@Column(name = "payment_time", columnDefinition = "TIME")
	private LocalTime paymentTime;

	@Column(name = "payment_date", columnDefinition = "DATE")
	private LocalDate paymentDate;

	@Column(name = "payment_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime paymentDateTime;
	/*
	 * @OneToOne(cascade =
	 * {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
	 * })
	 * 
	 * @JoinColumn(name = "order_details_id") private OrderDetails orderDetails;
	 */
	@JsonIgnore
	@OneToMany(mappedBy="paymentDetails", fetch=FetchType.LAZY, orphanRemoval =
			true,cascade=
		{CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
		}) 
	private Set<OrderDetails> orderDetails;

	public void addOrderDetails(OrderDetails order) {
		if(orderDetails==null)
			orderDetails = new HashSet<>();
		orderDetails.add(order);
		order.setPaymentDetails(this); 
	}

	public void removeOrderItem(OrderDetails order) {
		orderDetails.remove(order); 
		order.setPaymentDetails(null); 
	}
}
