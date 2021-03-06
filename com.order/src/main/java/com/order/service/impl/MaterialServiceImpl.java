package com.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.order.dao.DrinkCakeRepository;
import com.order.dao.MaterialRepository;
import com.order.dao.RecipeRepository;
import com.order.entity.Material;
import com.order.entity.Recipe;
import com.order.request.MaterialRequest;
import com.order.request.RecipeRequest;
import com.order.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public Material findById(int id) {
		Optional<Material> material = materialRepository.findById(id);
		return material.isPresent() ? material.get() : null;
	}

	@Override
	public List<Material> findAll() {
		return materialRepository.findAll();
	}

	@Override
	public boolean addMaterial(MaterialRequest request) {
		Material material = new Material(request);
		try {
			materialRepository.save(material);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateMaterial(int id, MaterialRequest request) {
		Material material = materialRepository.findById(id).get();
		if (material != null) {
			try {
				material.setName(request.getName());
				material.setQuality(request.getQuality());
				material.setAmount(request.getAmount());
				material.setType(request.getType());
				material.setUpdatedAt(new Date());
				material.setUpdateBy(request.getUpdatedBy());
				materialRepository.save(material);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean deleteMaterial(Integer id) {
		try {
			materialRepository.deleteById(id);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	@Override
	public boolean updateAmount(RecipeRequest request) {
		List<Recipe> recipes = recipeRepository.findByDrinkCake(request.getFoodId());
		try {
			for (Recipe recipe : recipes) {
				Material material = materialRepository.findById(recipe.getMaterial()).get();
				int amount = material.getAmount();
				amount -= request.getSize().equals("M") ? request.getAmountForOne() : request.getAmountForOne() * 2;
				material.setAmount(amount);
				material.setUpdateBy(request.getUpdatedBy());
				materialRepository.save(material);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Cacheable("material")
	public Material getBigMaterial() {
		return doSlow();
	}

	private Material doSlow() {
		Material m = new Material();
		try {
			Thread.sleep(2000L);
			m.setName("TechMaterial");
			return m;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m.setType(2);
		return m;
	}

}
