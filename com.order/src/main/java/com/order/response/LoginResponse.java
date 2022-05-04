package com.order.response;

public class LoginResponse {

	private Integer id;
	private String name;
	private String token;
	
	public LoginResponse() {
		
	}
	public LoginResponse(Integer id,String name,String token) {
		this.id =id;
		this.name=name;
		this.token=token;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
