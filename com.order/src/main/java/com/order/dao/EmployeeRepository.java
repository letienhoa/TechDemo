package com.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.Employee;

@Repository
public interface EmployeeRepository extends  JpaRepository<Employee, Integer>{

	Employee findByUserName(String userName);
	
	Employee findByUserNameAndPassword(String userName,String password);
}
