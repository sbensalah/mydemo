package com.demo.soumaya.servicees.impl;

import org.springframework.stereotype.Service;

import com.demo.soumaya.common.core.entities.User;
import com.demo.soumaya.common.core.repositories.IUserRepository;
import com.demo.soumaya.common.dto.UserDetailsDto;
import com.demo.soumaya.common.dto.UserDto;
import com.demo.soumaya.mappers.IMapperEntityDto;
import com.demo.soumaya.servicees.IUserService;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class UserServiceImpl  implements IUserService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	
	@Autowired
	private  IUserRepository userRepository;

	@Autowired
	private IMapperEntityDto<User, UserDto> mapperEntityDto;


	
	public UserDto loadUser(Long id) {
//		if(id == null) {
//			
//		}
		
        Optional<User> oUser = userRepository.findById(id);
//        if(!oUser.isPresent()) {
//        	
//        }
        
        User user = oUser.get();
        UserDto userDto = mapperEntityDto.mapEntityToDto(user);


		
		return userDto;
	 }
	 
	public UserDto createUser(UserDetailsDto userDetailsDto) {
		UserDto userDto = userDetailsDto.getUserDto();
		userDto.setCreationDate(new Date());
		//new date() create a current date which represents the date and time
		User u = mapperEntityDto.mapDtoToEntity(userDto);
		User createdUser = userRepository.save(u);
		
		UserDto createdUserDto = mapperEntityDto.mapEntityToDto(createdUser);
		return createdUserDto;
	}
}



