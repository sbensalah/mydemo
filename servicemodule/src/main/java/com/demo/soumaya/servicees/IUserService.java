package com.demo.soumaya.servicees;

import com.demo.soumaya.common.dto.UserDetailsDto;
import com.demo.soumaya.common.dto.UserDto;

public interface IUserService {
	
	 UserDto createUser(UserDetailsDto userDetailsDto);
	 
	 UserDto loadUser(Long id);
}
