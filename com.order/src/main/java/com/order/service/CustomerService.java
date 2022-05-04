package com.order.service;

import java.util.List;

import com.order.entity.Customer;
import com.order.request.CustomerRequest;

public interface CustomerService {

	Customer findById(int id);

	List<Customer> findAll();

	boolean add(CustomerRequest request);

	boolean update(CustomerRequest request);

	boolean delete(int id);

	boolean updatePoint(int customerId, Integer point);
	
	double getDiscountById(int id);
}