package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.DrinkCake;
import com.order.request.DrinkCakeRequest;
import com.order.service.DrinkCakeService;

@RestController
@RequestMapping("api/drinkcake")
public class DrinkCakeController {
	@Autowired
	private DrinkCakeService drinkCakeService;
	
	@RequestMapping(value = "/create", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> create(@RequestBody DrinkCakeRequest wrraper) {
		return ResponseEntity.ok(drinkCakeService.add(wrraper));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> update(@RequestBody DrinkCakeRequest wrraper) {
		return ResponseEntity.ok(drinkCakeService.update(wrraper));
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		return ResponseEntity.ok(drinkCakeService.delete(id));
	}
	
	@RequestMapping(value="getById/{id}", method= RequestMethod.GET)
	public ResponseEntity<DrinkCake> getById(@PathVariable int id) {
		return ResponseEntity.ok(drinkCakeService.findById(id));
	}
	
	@RequestMapping(value="getAll", method=RequestMethod.GET)
	public ResponseEntity<List<DrinkCake>> getAll() {
		return ResponseEntity.ok(drinkCakeService.findAll());
	} 
}
