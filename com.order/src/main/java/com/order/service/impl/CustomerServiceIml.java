package com.order.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.order.dao.CustomerRepository;
import com.order.entity.Customer;

import com.order.request.CustomerRequest;

import com.order.service.CustomerService;

@Service
public class CustomerServiceIml implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer findById(int id) {
		Optional<Customer> table = customerRepository.findById(id);
		return table.get();
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public boolean add(CustomerRequest request) {
		Customer tables = new Customer(request);
		tables.setCreatedAt(new Date());
		
		try {
			customerRepository.save(tables);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean update(CustomerRequest request) {
		Customer customer = this.findById(request.getId());
		
		if(customer == null) return false;
		
		customer.setPhone(request.getPhone());
		customer.setUpdatedAt(new Date());
		customer.setUpdateBy(request.getUpdatedBy());
		try {
			customerRepository.save(customer);
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try {
			customerRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	@Override
	public boolean updatePoint(int customerId, Integer point) {
		Customer customer = this.findById(customerId);
		if(customer == null) return false;
		
		customer.setPoint(point);
		customer.setUpdatedAt(new Date());
		try {
			customerRepository.save(customer);
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public double getDiscountById(int id) {
		Customer customer = this.findById(id);	
		return customer.getPoint()/10;
	}

}