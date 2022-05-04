package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Customer;
import com.order.request.CustomerRequest;

import com.order.service.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
	  
	  
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody CustomerRequest wrraper) {
		return ResponseEntity.ok(customerService.add(wrraper));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> update(@RequestBody CustomerRequest wrraper) {
		return ResponseEntity.ok(customerService.update(wrraper));
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		return ResponseEntity.ok(customerService.delete(id));
	}

	@RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getByPhone(@PathVariable int id) {
		return ResponseEntity.ok(customerService.findById(id));
	}

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAll() {
		return ResponseEntity.ok(customerService.findAll());
	}
	
	@RequestMapping(value = "getDiscountById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDiscountById(@PathVariable int id) {
		return ResponseEntity.ok(customerService.getDiscountById(id));
	}
}
