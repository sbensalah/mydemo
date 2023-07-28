package com.demo.soumaya.common.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	
	private String login;
	
	private String firstname;
	
	private String lastname;
	
	private String mail;
	
	private Date creationDate;
	
	private String password;
	


}
