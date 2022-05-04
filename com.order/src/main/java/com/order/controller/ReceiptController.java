package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.request.ReceiptRequest;
import com.order.service.ReceiptService;

@RestController
@RequestMapping("api/receipt")
public class ReceiptController {

	@Autowired
	private ReceiptService receiptlService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody ReceiptRequest wrraper) {
		return ResponseEntity.ok(receiptlService.addReceipt(wrraper));
	} 
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> update(
			@PathVariable int id,
			@RequestBody ReceiptRequest wrraper) {
		return ResponseEntity.ok(receiptlService.updateReceipt(id,wrraper));
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		return ResponseEntity.ok(receiptlService.deleteReceipt(id));
	}
	
	@RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByPhone(@PathVariable int id) {
		return ResponseEntity.ok(receiptlService.findById(id));
	}

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(receiptlService.findAll());
	}
	
	@RequestMapping(value = "/{id}/changeTable", method = RequestMethod.POST)
	public ResponseEntity<?> changeTable(@PathVariable int id,
			@RequestParam("tableId") int tableId) {
		return ResponseEntity.ok(receiptlService.changeTable(id, tableId));
	}
}
