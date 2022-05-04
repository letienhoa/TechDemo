package com.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.order.dao.TableRepository;
import com.order.entity.Tables;
import com.order.request.TableRequest;
import com.order.service.TablesService;

@Service
public class TablesServiceImpl implements TablesService {

	@Autowired
	private TableRepository tableRepository;

	@Override
	public boolean addTable(TableRequest request) {
		Tables tables = new Tables(request);
		try {
			tableRepository.save(tables);
		} catch (Exception e) {
			throw new HTTPException(500);
		}
		return true;
	}

	@Override
	public boolean updateTable(int id, TableRequest request) {
		Tables tables = findById(id);
		if (tables != null) {
			try {
				tables.setFloor(request.getFloor());
				tables.setStatus(request.getStatus());
				tables.setUpdatedAt(new Date());
				tableRepository.save(tables);
			} catch (Exception e) {
				throw new HTTPException(500);
			}
		} else
			return false;
		return true;
	}

	@Override
	public boolean deleteTables(Integer id) {
		if (findById(id) != null) {
			try {
				tableRepository.deleteById(id);
				return true;
			} catch (EmptyResultDataAccessException e) {
				throw new HTTPException(500);
			}
		} else
			return false;

	}

	@Override
	public Tables findById(int id) {
		Optional<Tables> table = tableRepository.findById(id);
		return table.get();
	}

	@Override
	public List<Tables> findAll() {
		return tableRepository.findAll();
	}

}
