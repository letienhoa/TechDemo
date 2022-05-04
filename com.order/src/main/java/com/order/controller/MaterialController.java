package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.request.MaterialRequest;
import com.order.request.RecipeRequest;
import com.order.service.MaterialService;

@RestController
@RequestMapping("api/material")
public class MaterialController {

	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value = "/updateAmountMaterial", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody RecipeRequest wrraper) {
		return ResponseEntity.ok(materialService.updateAmount(wrraper));
	} 
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody MaterialRequest wrapper) {
		return ResponseEntity.ok(materialService.addMaterial(wrapper));
	} 
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> update(
			@PathVariable int id,
			@RequestBody MaterialRequest wrapper) {
		return ResponseEntity.ok(materialService.updateMaterial(id,wrapper));
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		return ResponseEntity.ok(materialService.deleteMaterial(id));
	}
	
	@RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByPhone(@PathVariable int id) {
		return ResponseEntity.ok(materialService.findById(id));
	}

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(materialService.findAll());
	}
}
