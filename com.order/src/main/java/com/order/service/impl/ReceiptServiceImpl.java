package com.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.order.dao.CustomerRepository;
import com.order.dao.EmployeeRepository;
import com.order.dao.ReceiptRepository;
import com.order.entity.Customer;
import com.order.entity.Employee;
import com.order.entity.Receipt;
import com.order.request.ReceiptRequest;
import com.order.service.ReceiptService;

@Service
public class ReceiptServiceImpl implements ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Receipt findById(int id) {
		Optional<Receipt> receipt = receiptRepository.findById(id);
		return receipt.get()!=null?receipt.get():null;
	}

	@Override
	public List<Receipt> findAll() {
		return receiptRepository.findAll();
	}

	@Override
	public boolean addReceipt(ReceiptRequest request) {
		Optional<Employee> em = employeeRepository.findById(request.getEmployee());
		Optional<Customer> cus = customerRepository.findById(request.getCustomer());
		if(!em.isPresent()||!cus.isPresent()) {
			return false;
		}
		Receipt receipt = new Receipt(request, em.get(), cus.get());
		try {
			receiptRepository.save(receipt);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateReceipt(int id,ReceiptRequest request) {
		Optional<Employee> em = employeeRepository.findById(request.getEmployee());
		Optional<Customer> cus = customerRepository.findById(request.getCustomer());
		if(!em.isPresent()||!cus.isPresent()) {
			return false;
		}
		Receipt receipt = findById(id);
		if (receipt != null) {
			try {
				receipt.setCustomer(cus.get());
				receipt.setEmployee(em.get());
				receipt.setReceiptDetail(request.getReceiptDetail());
				receipt.setTable(request.getTableId());
				receipt.setTotalPrice(request.getTotalPrice());
				receipt.setTypePayment(request.getTypePayment());
				receipt.setTypeService(request.getTypeService());
				receipt.setUpdatedAt(new Date());
				receipt.setUpdateBy(request.getUpdatedBy());
				receiptRepository.save(receipt);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean deleteReceipt(Integer id) {
		try {
			receiptRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	@Override
	public boolean changeTable(int id, int tableId) {
		Receipt receipt = findById(id);
		if (receipt != null) {
			try {
				receipt.setTable(tableId);
				receipt.setUpdatedAt(new Date());
				receiptRepository.save(receipt);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	
	
	
}
