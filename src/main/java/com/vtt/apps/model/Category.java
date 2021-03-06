package com.vtt.apps.model;

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
@Table(name = "category") // the name user can be ambiguous for postgresql main tables.
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "image_url")
	private String imageurl;

	@OneToMany(mappedBy="category", fetch=FetchType.LAZY, orphanRemoval = true,cascade=
		{CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
		})
	private Set<SubCategory> subCategories;


}
