package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.request.TableRequest;
import com.order.service.TablesService;

@RestController
@RequestMapping("api/tables")
public class TableController {

	@Autowired
	private TablesService tableService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody TableRequest wrraper) {
		return ResponseEntity.ok(tableService.addTable(wrraper));
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> update(
			@PathVariable int id,
			@RequestBody TableRequest wrraper) {
		return ResponseEntity.ok(tableService.updateTable(id,wrraper));
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		return ResponseEntity.ok(tableService.deleteTables(id));
	}
	
	@RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByPhone(@PathVariable int id) {
		return ResponseEntity.ok(tableService.findById(id));
	}

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(tableService.findAll());
	}
}
