package com.order.service;

import java.util.List;

import com.order.entity.DrinkCake;
import com.order.request.DrinkCakeRequest;



public interface DrinkCakeService {
	DrinkCake findById(int id);
	
	List<DrinkCake> findAll();

	boolean add(DrinkCakeRequest request);
	
	boolean update(DrinkCakeRequest request);
	
	boolean delete(Integer id);
}
