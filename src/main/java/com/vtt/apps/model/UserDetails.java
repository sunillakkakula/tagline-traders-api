package com.vtt.apps.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    
    @Column(name = "contact_no")
    private String contactNo;
    
    @Column(name = "email_id")
    private String emailId;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

	/*
	 * @OneToMany(mappedBy="userDetails", fetch=FetchType.LAZY, orphanRemoval =
	 * true,cascade=
	 * {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE
	 * }) private Set<OrderDetails> orderDetails;
	 */
}
