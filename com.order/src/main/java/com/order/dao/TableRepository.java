package com.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.Tables;

@Repository
public interface TableRepository extends JpaRepository<Tables, Integer>{

}
