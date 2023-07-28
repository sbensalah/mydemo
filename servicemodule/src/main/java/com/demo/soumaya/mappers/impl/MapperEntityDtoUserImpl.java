package com.demo.soumaya.mappers.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.demo.soumaya.common.core.entities.User;
import com.demo.soumaya.common.dto.UserDto;
import com.demo.soumaya.mappers.IMapperEntityDto;


@Component
public class MapperEntityDtoUserImpl implements IMapperEntityDto<User, UserDto>{
	
	public
	UserDto mapEntityToDto(User e) {
		if(e == null)
			return null;
		
		UserDto d = new UserDto();
		BeanUtils.copyProperties(e, d);
		return d;
		
		
	}
	
	public
	User mapDtoToEntity(UserDto d) {
		if(d == null)
			return null;
		
		User e = new User();
		BeanUtils.copyProperties(d, e);
		return e;
	}

}
