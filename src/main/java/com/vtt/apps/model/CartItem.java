package com.vtt.apps.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author slakkakula
 *
 */
@Entity
@Table(name = "cart_item") // the name user can be ambiguous for postgresql main tables.
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	private String name;

	@Column(name = "imageurl")
	private String imageurl;

	@Column(name = "brand")
	private String brand;

	@Column(name = "description")
	private String description;

	@Column(name = "isTaxable")
	private Boolean isTaxable;

	@Column(name = "taxPercent")
	private Float taxPercent;
	
	@Column(name = "order_type")
	private String orderType;
	
	@Column(name = "selected_uom")
	private String selectedUom;

	@Column(name = "qty")
	private Integer qty;

	@Column(name = "unit_price")
	private Float unitPrice;

	@Column(name = "selling_price")
	private Float sellingPrice;

	@Column(name = "mrp")
	private Float mrp;
	
	
	@JsonIgnore	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH},fetch= FetchType.LAZY)
	@JoinColumn(name="cart_details_id", nullable = false)
	private CartDetails cartDetails;

}
