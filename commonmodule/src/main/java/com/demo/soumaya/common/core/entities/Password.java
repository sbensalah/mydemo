package com.demo.soumaya.common.core.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = Password.PASSWORDS)
@Data
public class Password {
	public static final String PASSWORDS = "PASSWORDS";
	
	public static final String PASSWORDS_SEQ = "PASSWORDS_SEQ";
	
	@Id
	@SequenceGenerator(name = PASSWORDS_SEQ, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PASSWORDS_SEQ)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	private String password;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date updateDate;
	

}
