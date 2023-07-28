package com.demo.soumaya.dto;


import lombok.Data;

@Data
public class DataChangeDto {
	
	private Long id;
	
	private Long lastChange;
	
	public DataChangeDto(Long id, Long lastChange) {
		this.id =id;
		this.lastChange = lastChange;
	}
}
