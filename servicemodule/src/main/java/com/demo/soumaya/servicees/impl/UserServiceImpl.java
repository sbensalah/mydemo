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

	public static final int LENGTH = 5;
	
	public static final String PERCENT = "%";

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IMapperEntityDto<User, UserDto> mapperEntityDto;

	
	 public List<UserDto> loadUsersByLogin(final String login) throws UserException {
			if ((login == null) || StringUtils.isBlank(login)) {
				throw new UserException("login is empty");
			}
			String label = PERCENT + login + PERCENT;
			List<UserDto> usersDto = userRepository.findAllByLoginLikeIgnoreCase(label).stream().map(u -> mapperEntityDto.mapEntityToDto(u))
					.collect(Collectors.toList());
			
		 return usersDto;
	 }
	public UserDto loadUserByLogin(final String login) throws UserException {
		if ((login == null) || StringUtils.isBlank(login)) {
			throw new UserException("login is empty");
		}
		String label = PERCENT + login + PERCENT;
		Optional<User> oUser = userRepository.findByLoginLikeIgnoreCase(label);
		if (!oUser.isPresent()) {
			String msg = String.format("no user with login [%s] ", login);
			throw new UserException(msg);
		}
		User u = oUser.get();

		return mapperEntityDto.mapEntityToDto(u);
	}

	public List<UserDto> loadUsersByFilter(Long filterId) throws UserException {
		if (filterId == null) {
			throw new UserException("no filter id informed ");
		}

		List<UserDto> usersDto = StreamSupport.stream(userRepository.findAll().spliterator(), false)
				.filter(e -> e.getId() > filterId).map(e -> mapperEntityDto.mapEntityToDto(e))
				.collect(Collectors.toList());
		return usersDto;
	}

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

		return mapperEntityDto.mapEntityToDto(createdUser);
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

	public List<UserDto> loadUsers() throws UserException {
		List<UserDto> usersDto = StreamSupport.stream(userRepository.findAll().spliterator(), false)
				.map(u -> mapperEntityDto.mapEntityToDto(u)).collect(Collectors.toList());

		return usersDto;
	}

}
