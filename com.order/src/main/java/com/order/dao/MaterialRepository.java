package com.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer>{

}
