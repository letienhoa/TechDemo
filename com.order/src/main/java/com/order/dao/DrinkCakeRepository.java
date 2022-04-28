package com.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.DrinkCake;

@Repository
public interface DrinkCakeRepository extends JpaRepository<DrinkCake,Integer>{

}
