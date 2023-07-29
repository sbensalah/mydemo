package com.demo.soumaya.servicees;



import java.util.List;

import com.demo.soumaya.common.dto.UserDto;
import com.demo.soumaya.exceptions.UserException;

public interface IUserService {
	
	 UserDto createUser(final UserDto userDto) throws UserException;
	 
	 UserDto loadUser(final Long id) throws UserException;
	 
	 UserDto loadUserByLogin(final String login) throws UserException;
	 
	 List<UserDto> loadUsersByLogin(final String login) throws UserException;
	 
	 List<UserDto> loadUsers() throws UserException;
	 
	 UserDto updateUser(final UserDto userDto) throws UserException;
	 
	 void deleteUser(final Long id) throws UserException;
	 
	 List<UserDto> loadUsersByFilter(Long filterId) throws UserException;	 
}
