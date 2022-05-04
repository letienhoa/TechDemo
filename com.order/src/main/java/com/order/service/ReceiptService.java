package com.order.service;

import java.util.List;
import com.order.entity.Receipt;
import com.order.request.ReceiptRequest;

public interface ReceiptService {

	Receipt findById(int id);
	
	List<Receipt> findAll();

	boolean addReceipt(ReceiptRequest request);
	
	boolean updateReceipt(int id,ReceiptRequest request);
	
	boolean deleteReceipt(Integer id);
	
	boolean changeTable(int id,int tableId);
}
