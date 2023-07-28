package com.demo.soumaya.common.core.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;


import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = User.USERS)
@Data
public class User {
	
	public static final String USERS = "USERS";
	
	public static final String USERS_SEQ = "USERS_SEQ";
	
	@Id
	@SequenceGenerator(name = USERS_SEQ, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USERS_SEQ)
	private Long id;
	
	@Column(unique =  true, nullable = false, updatable = false)
	@Size(min = 5)
	private String login;
	
	private String firstname;
	
	private String lastname;
	
	private String mail;

	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "password_id")
	Password pwd;
	
}
