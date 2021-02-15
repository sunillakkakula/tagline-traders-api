/*
 * package com.vtt.apps.model;
 * 
 * import java.util.Set;
 * 
 * import javax.persistence.CascadeType; import javax.persistence.Column; import
 * javax.persistence.Entity; import javax.persistence.FetchType; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.ManyToOne; import javax.persistence.OneToMany; import
 * javax.persistence.OneToOne; import javax.persistence.Table;
 * 
 * import com.fasterxml.jackson.annotation.JsonIgnore; import
 * com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 * 
 * import lombok.AllArgsConstructor; import lombok.Getter; import
 * lombok.NoArgsConstructor; import lombok.Setter;
 *//**
	 * @author lskumar
	 * @date 2020-02-09
	 * @time 12:52
	 *//*
		 * 
		 * @Entity
		 * 
		 * @Table(name = "order_details") // the name user can be ambiguous for
		 * postgresql main tables.
		 * 
		 * @Setter
		 * 
		 * @Getter
		 * 
		 * @AllArgsConstructor
		 * 
		 * @NoArgsConstructor
		 * 
		 * @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) public class
		 * OrderDetails_BKP {
		 * 
		 * @Id
		 * 
		 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
		 * 
		 * 
		 * @Column(name = "name") private String name;
		 * 
		 * 
		 * // @OneToOne(mappedBy = "orderDetails",fetch=FetchType.LAZY, orphanRemoval =
		 * true,cascade =
		 * {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
		 * }) // private PaymentDetails paymentDetails;
		 * 
		 * @OneToMany(targetEntity=Product.class,fetch=FetchType.LAZY, orphanRemoval =
		 * true) private Set<Product> products;
		 * 
		 * 
		 * @ManyToOne(fetch = FetchType.LAZY, optional = false)
		 * 
		 * @JoinColumn(name = "user_details_id", nullable = false)
		 * 
		 * @JsonIgnore private UserDetails userDetails;
		 * 
		 * 
		 * @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true,cascade =
		 * {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
		 * },optional=true ) // @JoinColumn(name = "shipping_details_id", nullable =
		 * true ,insertable = true,updatable = true)
		 * 
		 * @JsonIgnore private ShippingDetails shippingDetails;
		 * 
		 * 
		 * }
		 */