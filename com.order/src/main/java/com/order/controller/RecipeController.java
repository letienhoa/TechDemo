package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.request.RecipeRequest;
import com.order.service.RecipeService;

@RestController
@RequestMapping("api/recipe")
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody RecipeRequest wrraper) {
		return ResponseEntity.ok(recipeService.addRecipe(wrraper));
	} 
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public ResponseEntity<Boolean> update(
			@PathVariable int id,
			@RequestBody RecipeRequest wrraper) {
		return ResponseEntity.ok(recipeService.updateRecipe(id,wrraper));
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> delete(@PathVariable int id) {
		return ResponseEntity.ok(recipeService.deleteRecipe(id));
	}
	
	@RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable int id) {
		return ResponseEntity.ok(recipeService.findById(id));
	}

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(recipeService.findAll());
	}
}
