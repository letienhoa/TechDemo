package com.order.service;

import java.util.List;

import com.order.entity.Recipe;
import com.order.request.RecipeRequest;

public interface RecipeService {

	Recipe findById(int id);

	List<Recipe> findAll();

	boolean addRecipe(RecipeRequest request);

	boolean updateRecipe(int id, RecipeRequest request);

	boolean deleteRecipe(Integer id);
}
