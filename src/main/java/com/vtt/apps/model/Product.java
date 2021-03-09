package com.vtt.apps.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "product") // the name user can be ambiguous for postgresql main tables.
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "imageurl")
	private String imageurl;

	@Column(name = "brand")
	private String brand;

	@Column(name = "description")
	private String description;

	@Column(name = "count_in_stock")
	private Integer countInStock;

	@Column(name = "is_taxable")
	private Boolean isTaxable;

	@Column(name = "tax_percent")
	private Float taxPercent;
	
	@Column(name = "order_type")
	private String orderType;
	
	@Column(name = "selected_uom")
	private String selectedUom;
	
	@Column(name = "is_vtt_best_seller")
	private Boolean isVttBestSeller;
	
	/*
	 * @OneToOne(mappedBy="product", fetch=FetchType.LAZY, orphanRemoval =
	 * true,cascade=
	 * {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
	 * })
	 * 
	 * @Column(name = "cart_item_id") private CartItem cartItem;
	 */
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sub_category_id", nullable = false)
//	@JsonManagedReference
	@JsonIgnore
	private SubCategory subCategory;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY, orphanRemoval = true,cascade=
		{CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
		}) 
	private Set<AvailableInBulk> availableInBulk;

	@OneToMany(mappedBy="product", fetch=FetchType.LAZY, orphanRemoval = true,cascade=
		{CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
		})
	private Set<AvailableInDomestic> availableInDomestic;
	
}
