package com.order.service;

import java.util.List;

import com.order.entity.Material;
import com.order.request.MaterialRequest;
import com.order.request.RecipeRequest;

public interface MaterialService {
	Material findById(int id);
	
	List<Material> findAll();

	boolean addMaterial(MaterialRequest request);
	
	boolean updateMaterial(int id,MaterialRequest request);
	
	boolean deleteMaterial(Integer id);
	
	boolean updateAmount(RecipeRequest request);
	
	Material getBigMaterial();
}
