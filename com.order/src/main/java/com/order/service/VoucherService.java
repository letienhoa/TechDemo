package com.order.service;

import java.util.List;

import com.order.entity.Voucher;
import com.order.request.VoucherRequest;

public interface VoucherService {
	Voucher findById(int id);
	boolean createVoucher(VoucherRequest voucher);
	boolean updateVoucher(VoucherRequest voucher);
	boolean deleteVoucher(int id);
	List<Voucher> getList();
	List<Voucher> getPublishList();
}
