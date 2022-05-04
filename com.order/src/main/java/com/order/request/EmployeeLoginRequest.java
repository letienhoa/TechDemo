package com.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeLoginRequest {
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("password")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
