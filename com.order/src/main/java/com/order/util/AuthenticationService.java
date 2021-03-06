package com.order.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.config.JwtService;
import com.order.response.UserToken;
import com.order.service.EmployeeService;

@Service
public class AuthenticationService {

	@Autowired
	JwtService jwtService;
	
	@Autowired
	EmployeeService employeeService;
	
	public UserToken getUserNameToken(String token) {
		String userName = jwtService.getUsernameFromToken(token);
		UserToken userToken = employeeService.findByUsername(userName);
		return userToken;	
	} 
}
