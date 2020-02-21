package com.imamesta.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
	
	private String password;
	
	public UserDto() {
		
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
