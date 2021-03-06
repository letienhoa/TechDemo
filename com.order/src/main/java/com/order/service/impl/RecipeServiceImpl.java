package com.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.DrinkCakeRepository;
import com.order.dao.MaterialRepository;
import com.order.dao.RecipeRepository;
import com.order.entity.DrinkCake;
import com.order.entity.Material;
import com.order.entity.Recipe;
import com.order.request.RecipeRequest;
import com.order.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private DrinkCakeRepository drinkCakeRepository;

	@Autowired
	private MaterialRepository materialRepository;

	@Override
	public Recipe findById(int id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		return recipe.isPresent()?recipe.get():null;
	}

	@Override
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}

	@Override
	public boolean addRecipe(RecipeRequest request) {
		Optional<DrinkCake> drinkCakeO = drinkCakeRepository.findById(request.getFoodId());
		Optional<Material> materialO = materialRepository.findById(request.getMaterialId());
		if (drinkCakeO.isPresent() && materialO.isPresent()) {
			Recipe re = new Recipe();
			re.setDrinkCake(drinkCakeO.get().getId());
			re.setMaterial(materialO.get().getId());
			re.setAmountForOne(request.getAmountForOne());
			re.setSize(request.getSize());
			re.setUpdateBy(request.getUpdatedBy());
			re.setCreatedBy(request.getCreatedBy());
			try {
				recipeRepository.save(re);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean updateRecipe(int id, RecipeRequest request) {
		Optional<Recipe> recipeO = recipeRepository.findById(id);
		if (recipeO.isPresent()) {
			Recipe re = recipeO.get();
			Optional<DrinkCake> drinkCakeO = drinkCakeRepository.findById(request.getFoodId());
			Optional<Material> materialO = materialRepository.findById(request.getMaterialId());
			if(drinkCakeO.isPresent() && materialO.isPresent()) {
				re.setDrinkCake(drinkCakeO.get().getId());
				re.setMaterial(materialO.get().getId());
				re.setAmountForOne(request.getAmountForOne());
				re.setSize(request.getSize());
				re.setUpdatedAt(new Date());
				re.setUpdateBy(request.getUpdatedBy());
				try {
					recipeRepository.save(re);
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public boolean deleteRecipe(Integer id) {
		Optional<Recipe> recipeO = recipeRepository.findById(id);
		if(recipeO.isPresent()) {
			try {
				recipeRepository.deleteById(id);
				return true;
			} catch (Exception e) {
			return false;
			}
		}
		return false;
	}

}
