package com.order.response;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.order.entity.Employee;

public class UserToken {

	private String userName;

	private String Password;
	@Column(name="permission")
	private String permission;

	private int id;

	private String name;

	private String email;

	private int discount;

	public UserToken() {

	}

	public UserToken(Employee entity) {
		this.userName = entity.getUserName();
		this.Password = entity.getPassword();
		this.permission=entity.getPermission();
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(permission));
		return authorities;
	}
}
