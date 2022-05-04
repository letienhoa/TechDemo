package com.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.order.dao.VoucherRepository;
import com.order.entity.Voucher;
import com.order.request.VoucherRequest;
import com.order.service.VoucherService;

@Service
public class VoucherServiceIml implements VoucherService {
	@Autowired
	private VoucherRepository voucherRepository;
	
	@Override
	public Voucher findById(int id) {
		try {
			Optional<Voucher> voucher = this.voucherRepository.findById(id);
			return voucher.get();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean createVoucher(VoucherRequest request) {
		Voucher voucher = new Voucher(request);
		
		voucher.setCreatedAt(new Date());
		voucher.setCreatedBy(request.createdBy);
		
		try {
			this.voucherRepository.save(voucher);
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean updateVoucher(VoucherRequest request) {
		Voucher voucher = this.findById(request.id);
		if(voucher == null) return false;
		
		voucher.name = request.name;
		voucher.foodId = request.value != null ? null : request.foodId;
		voucher.value = request.value;
		voucher.publishedDate = request.publishedDate;
		voucher.endDate = request.endDate;
		voucher.setUpdatedAt(new Date());
		voucher.setUpdateBy(request.updatedBy);
		
		try {
			this.voucherRepository.save(voucher);
		}
		catch(Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteVoucher(int id) {
		// TODO Auto-generated method stub
		try {
			this.voucherRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public List<Voucher> getList() {
		// TODO Auto-generated method stub
		try {
			List<Voucher> vouchers = this.voucherRepository.findAll();
			return vouchers;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Voucher> getPublishList() {
		// TODO Auto-generated method stub
		try {
			List<Voucher> vouchers = this.voucherRepository.getPublishList(new Date());
			return vouchers;
		}catch(Exception e) {
			return null;
		}
	}
	
}
