package com.demo.soumaya.mappers;

public interface IMapperEntityDto<E,D> {
	
	D mapEntityToDto(E e);
	E mapDtoToEntity(D d);
	
	void mapDtoEntity(D d, E e);

}
