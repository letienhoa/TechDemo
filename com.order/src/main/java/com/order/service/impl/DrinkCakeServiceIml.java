package com.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.order.dao.DrinkCakeRepository;
import com.order.entity.DrinkCake;
import com.order.request.DrinkCakeRequest;
import com.order.service.DrinkCakeService;

@Service
public class DrinkCakeServiceIml implements DrinkCakeService {
	@Autowired
	private DrinkCakeRepository drinkCakeRepository;

	@Override
	public DrinkCake findById(int id) {
		Optional<DrinkCake> table = drinkCakeRepository.findById(id);
		return table.get();
	}

	@Override
	public List<DrinkCake> findAll() {
		return drinkCakeRepository.findAll();
	}

	@Override
	public boolean add(DrinkCakeRequest request) {
		DrinkCake drinkCake = new DrinkCake(request);
		drinkCake.setCreatedAt(new Date());
		try {
			drinkCakeRepository.save(drinkCake);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean update(DrinkCakeRequest request) {
		DrinkCake data = this.findById(request.getId());
		if(data == null) return false;
		
		data.setName(request.getName());
		data.setPrice(request.getPrice());
		data.setUpdatedAt(new Date());
		data.setUpdateBy(request.getUpdatedBy());
		
		try {
			drinkCakeRepository.save(data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Integer id) {
		try {
			drinkCakeRepository.deleteById(id);
			return true;
		}
		catch(EmptyResultDataAccessException e) {
			return false;
		}
	}

}
