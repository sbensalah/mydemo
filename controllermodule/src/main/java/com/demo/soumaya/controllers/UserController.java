package com.demo.soumaya.controllers;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.soumaya.common.dto.UserDetailsDto;
import com.demo.soumaya.common.dto.UserDto;
import com.demo.soumaya.servicees.IUserService;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/employee")
public class UserController {


	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private IUserService userService;
	
	@PostMapping({"/"})
	public ResponseEntity<UserDto> createUser(@RequestBody UserDetailsDto userDetailsDto){
		
		UserDto userDto = userService.createUser(userDetailsDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@GetMapping({"/{userId}"})
	public ResponseEntity<UserDto> loadUser(@PathVariable("userId") Long id){
		

		UserDto userDto = userService.loadUser(id);
		
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	
}
