package com.vtt.apps.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "user_details") // the name user can be ambiguous for postgresql main tables.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "email_id")
	private String emailId;

	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	/* OneToOne UNI DIRECTIONAL */
	@OneToOne
	@JoinColumn(name = "cart_details_id")
	private CartDetails cartDetails;

	/* OneTomany UNI DIRECTIONAL  UserDetails --> OrderDetials*/
//	@JsonManagedReference
	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany( mappedBy = "userDetails", cascade = CascadeType.ALL,
	 * orphanRemoval = true) private Set<OrderDetails> orderDetailsSet= new
	 * HashSet<>();
	 * 
	 * public void addOrder(OrderDetails orderDetails) {
	 * orderDetailsSet.add(orderDetails); orderDetails.setUserDetails(this); }
	 * 
	 * public void removeorderDetails(OrderDetails orderDetails) {
	 * orderDetailsSet.remove(orderDetails); orderDetails.setUserDetails(null); }
	 */
}
