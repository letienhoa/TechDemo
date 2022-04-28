package com.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.entity.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer>{

}
