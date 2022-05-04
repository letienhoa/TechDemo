package com.order.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.order.config.JwtService;
import com.order.dao.EmployeeRepository;
import com.order.entity.Employee;
import com.order.request.EmployeeLoginRequest;
import com.order.request.EmployeeRequest;
import com.order.response.LoginResponse;
import com.order.response.UserToken;
import com.order.service.EmployeeService;
import com.order.util.PasswordEncryption;

@Service
public class EmployeeServiceIml implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public Employee findById(int id) {
		Optional<Employee> table = employeeRepository.findById(id);
		return table.get();
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public boolean add(EmployeeRequest request) {
		String passHash=null;
		PasswordEncryption pe = new PasswordEncryption();
		try {
			passHash = pe.convertHashToString(request.getPassword());
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		if(employeeRepository.findByUserName(request.getUserName())==null) {
			Employee employee = new Employee(request);
			employee.setPassword(passHash);
			employee.setCreatedAt(new Date());
			try {
				employeeRepository.save(employee);
				return true;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return false;
	}

	@Override
	public boolean update(EmployeeRequest request) {
		Employee employee = new Employee(request);
		employee.setUpdatedAt(new Date());
		employee.setPermission(request.getPermission());
		employee.setBirthday(request.getBirthday());
		employee.setPhone(request.getPhone());
		employee.setAddress(request.getAddress());
		employee.setUpdateBy(request.getUpdatedBy());
		
		try {
			employeeRepository.save(employee);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			employeeRepository.deleteById(id);
			return true;
		}
		catch(EmptyResultDataAccessException e) {
			return false;
		}
	}

	@Override
	public Employee findByUsernameAndPass(String userName, String pass) {
		Employee em = employeeRepository.findByUserNameAndPassword(userName, pass);
		return em;
	}

	@Override
	public UserToken findByUsername(String userName) {
		UserToken u=null;
		Employee em = employeeRepository.findByUserName(userName);
		if(em!=null) {
			u = new UserToken(em);
		}
		return u;
	}

	@Override
	public LoginResponse login(EmployeeLoginRequest request) {
		
		String token =null;
		String matKhauMK = null;
		UserToken user = new UserToken();
		Employee em = new Employee();
		PasswordEncryption pe = new PasswordEncryption();
		try {
			matKhauMK = pe.convertHashToString(request.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			em = employeeRepository.findByUserNameAndPassword(request.getUserName(), matKhauMK);
			user = new UserToken(em);
			if (user!= null) {
				token = jwtService.generateTokenLogin(user.getUserName());
				return new LoginResponse(em.getId(),em.getName(),token);
			}
		} catch (Exception ex) {
			
		}
		return null;
	}
	

}
