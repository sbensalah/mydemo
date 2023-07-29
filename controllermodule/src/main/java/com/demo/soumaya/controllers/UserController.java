package com.demo.soumaya.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.soumaya.common.dto.UserDto;
import com.demo.soumaya.dto.DataChangeDto;
import com.demo.soumaya.exceptions.UserException;
import com.demo.soumaya.servicees.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping({ "/" })
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws UserException {
		UserDto userDtoCreated = userService.createUser(userDto);
		return new ResponseEntity<>(userDtoCreated, HttpStatus.CREATED);
	}

	@GetMapping({ "/loadById/{userId}" })
	public ResponseEntity<UserDto> loadUser(@PathVariable("userId") Long id) throws UserException {
		UserDto userDto = userService.loadUser(id);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@GetMapping({ "/loadByLogin/{login}" })
	public ResponseEntity<UserDto> loadUserByLogin(@PathVariable("login") String login) throws UserException {
		UserDto userDto = userService.loadUserByLogin(login);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@GetMapping({ "/loadByLoginAll/{login}" })
	public ResponseEntity<List<UserDto>> loadUsersByLogin(@PathVariable("login") String login) throws UserException {
		List<UserDto> usersDto = userService.loadUsersByLogin(login);
		return new ResponseEntity<>(usersDto, HttpStatus.OK);
	}

	@PutMapping({ "/update" })
	public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) throws UserException {
		UserDto userDtoUpdated = userService.updateUser(userDto);

		return new ResponseEntity<>(userDtoUpdated, HttpStatus.OK);
	}

	@DeleteMapping({ "/delete/{userId}" })
	public ResponseEntity<DataChangeDto> delete(@PathVariable Long userId) throws UserException {
		userService.deleteUser(userId);
		return new ResponseEntity<>(new DataChangeDto(userId, new Date().getTime()), HttpStatus.OK);
	}

	@GetMapping({ "/allUsers" })
	public ResponseEntity<List<UserDto>> loadUsers() throws UserException {
		List<UserDto> list = userService.loadUsers();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping({ "/allUsers/{filterId}" })
	public ResponseEntity<List<UserDto>> loadUsersByFilter(@PathVariable Long filterId) throws UserException {
		List<UserDto> list = userService.loadUsersByFilter(filterId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
