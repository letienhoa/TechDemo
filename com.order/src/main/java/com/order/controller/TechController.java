package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.MaterialService;

@RestController
@RequestMapping("api/tech")
@EnableCaching
public class TechController {

	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(materialService.getBigMaterial());
	}
}
