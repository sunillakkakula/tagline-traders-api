package com.vtt.apps.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@Column(name = "payment_date")
	private LocalDateTime paymentDate;
	
	@OneToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "order_details_id")
	private OrderDetails orderDetails;
}
