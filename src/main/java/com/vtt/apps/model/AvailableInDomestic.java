package com.vtt.apps.model;

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
 * @author lskumar
 * @date 2020-02-09
 * @time 12:52
 */

@Entity
@Table(name = "available_in_domestic") // the name user can be ambiguous for postgresql main tables.
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class AvailableInDomestic
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "unit_of_messure")
	private String unitOfMessure;

	@Column(name = "qty")
	private Integer qty;

	@Column(name = "unit_price")
	private Float unitPrice;

	@Column(name = "selling_price")
	private Float sellingPrice;

	@Column(name = "mrp")
	private Float mrp;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
	private Product product;
    
}
