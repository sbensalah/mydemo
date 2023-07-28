package com.demo.soumaya.exceptions;

import lombok.Getter;

public enum UserErrorCodes {
	
	
	USER_LOGIN_REQUIRED(1),
	
	USER_MAIL_INVALID(2),
	USER_LOGIN_MIN_LENGTH_REQUIRED(3);
	
	private static final char DOT = '.';
	private static final char UNDERSCORE = '_';
	@Getter
	private int code;
	
	private UserErrorCodes(int code){
		this.code = code;
	}
	
	public String getId() {
		return this.name().replace(UNDERSCORE, DOT).toLowerCase();
	}

}
