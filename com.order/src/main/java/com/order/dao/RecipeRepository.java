package com.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.Recipe;

@Repository
public interface RecipeRepository  extends JpaRepository<Recipe, Integer>{

	List<Recipe> findByDrinkCake(int id);
}
