package com.demo.soumaya.servicees.impl;

import org.springframework.stereotype.Service;

import com.demo.soumaya.common.core.entities.Password;
import com.demo.soumaya.common.core.entities.User;
import com.demo.soumaya.common.core.repositories.IUserRepository;
import com.demo.soumaya.common.dto.UserDto;
import com.demo.soumaya.exceptions.UserException;
import com.demo.soumaya.mappers.IMapperEntityDto;
import com.demo.soumaya.servicees.IUserService;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static final int LENGTH = 5;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IMapperEntityDto<User, UserDto> mapperEntityDto;

	public UserDto loadUser(final Long id) throws UserException {
		if (id == null) {
			throw new UserException("id is null");
		}
		Optional<User> oUser = userRepository.findById(id);
		if (!oUser.isPresent()) {
			throw new UserException("User is not found");
		}
		User user = oUser.get();
		UserDto userDto = mapperEntityDto.mapEntityToDto(user);

		return userDto;
	}

	public UserDto createUser(final UserDto userDto) throws UserException {
		if ((userDto == null) || StringUtils.isBlank(userDto.getLogin())) {
			throw new UserException("login is empty");
		}
		if ((userDto.getLogin().length() < LENGTH)) {
			throw new UserException("login'length is < 5");
		}
		userDto.setCreationDate(new Date());
		User u = mapperEntityDto.mapDtoToEntity(userDto);
		if (userDto.getPassword() != null) {
			Password pwd = new Password();
			pwd.setPassword(userDto.getPassword());
			pwd.setCreationDate(new Date());
			u.setPwd(pwd);
		}
		User createdUser = userRepository.save(u);
		UserDto createdUserDto = mapperEntityDto.mapEntityToDto(createdUser);
		if (createdUser.getPwd() != null) {
			createdUserDto.setPassword(createdUser.getPwd().getPassword());
		}
		return createdUserDto;
	}

	private User checkUserExistanceById(final Long id) throws UserException {
		Optional<User> oUser = userRepository.findById(id);
		if (!oUser.isPresent()) {
			String msg = String.format("User does not exist for id [%s]", id.toString());
			throw new UserException(msg);
		}
		return oUser.get();
	}

	public UserDto updateUser(final UserDto userDto) throws UserException {
		if ((userDto == null) || StringUtils.isBlank(userDto.getLogin())) {
			throw new UserException("login is empty");
		}
		User u = checkUserExistanceById(userDto.getId());
		mapperEntityDto.mapDtoEntity(userDto, u);
		User updatedUser = userRepository.save(u);
		UserDto createdUserDto = mapperEntityDto.mapEntityToDto(updatedUser);
		if (updatedUser.getPwd() != null) {
			createdUserDto.setPassword(updatedUser.getPwd().getPassword());
		}
		return createdUserDto;
	}

	public void deleteUser(final Long id) throws UserException {
		if (checkUserExistanceById(id) != null) {
			userRepository.deleteById(id);
		}
	}
	
	 public List<UserDto> loadUsers() throws UserException{
		 List<User> listUser = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
		 List<UserDto> listUserDto = listUser.stream().map(u -> mapperEntityDto.mapEntityToDto(u)).collect(Collectors.toList());
		 return listUserDto;
	 }

}
