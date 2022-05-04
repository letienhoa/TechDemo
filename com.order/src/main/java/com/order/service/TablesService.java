package com.order.service;

import java.util.List;

import com.order.entity.Tables;
import com.order.request.TableRequest;

public interface TablesService {
	
	Tables findById(int id);
	
	List<Tables> findAll();

	boolean addTable(TableRequest request);
	
	boolean updateTable(int id,TableRequest request);
	
	boolean deleteTables(Integer id);
}
