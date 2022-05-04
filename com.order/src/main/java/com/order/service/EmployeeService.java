package com.order.service;

import java.util.List;

import com.order.entity.Employee;
import com.order.request.EmployeeLoginRequest;
import com.order.request.EmployeeRequest;
import com.order.response.LoginResponse;
import com.order.response.UserToken;


public interface EmployeeService {

	Employee findByUsernameAndPass(String userName, String pass);
	
	UserToken findByUsername(String userName);
	
	Employee findById(int id);
	
	List<Employee> findAll();

	boolean add(EmployeeRequest request);
	
	boolean update(EmployeeRequest request);
	
	boolean delete(Integer id);
	
	LoginResponse login(EmployeeLoginRequest request);
}
