package com.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Employee;
import com.order.request.EmployeeLoginRequest;
import com.order.request.EmployeeRequest;

import com.order.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Boolean> create(@RequestBody EmployeeRequest body){
		return ResponseEntity.ok(this.employeeService.add(body));
	}
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> update(@RequestBody EmployeeRequest body){
		return ResponseEntity.ok(employeeService.update(body));
	}
	
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> update(@PathVariable int id ){
		return ResponseEntity.ok(employeeService.delete(id));
	}
	
	@RequestMapping(value="getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getById(@PathVariable int id ){
		return ResponseEntity.ok(employeeService.findById(id));
	}

	@RequestMapping(value="getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAll(){
		return ResponseEntity.ok(employeeService.findAll());
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(HttpServletRequest request, @RequestBody EmployeeLoginRequest wrapper) {
		return ResponseEntity.ok(employeeService.login(wrapper));
	}
}
