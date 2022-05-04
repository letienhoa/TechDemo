package com.order.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Voucher;
import com.order.request.VoucherRequest;
import com.order.service.VoucherService;

@RestController
@RequestMapping("api/voucher")
public class VoucherController {
	@Autowired
	private VoucherService voucherService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Boolean> create(@RequestBody VoucherRequest voucher){
		boolean result = this.voucherService.createVoucher(voucher);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Voucher> getById(@PathVariable int id){
		Voucher voucher = this.voucherService.findById(id);
		return ResponseEntity.ok(voucher);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> update(@RequestBody VoucherRequest voucher){
		boolean result = this.voucherService.updateVoucher(voucher);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable int id){
		boolean result = this.voucherService.deleteVoucher(id);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public ResponseEntity<List<Voucher>> getList(){
		return ResponseEntity.ok(this.voucherService.getList());
	}
	
	@RequestMapping(value = "/getPublishList", method = RequestMethod.GET)
	public ResponseEntity<List<Voucher>> getPublishList(){
		return ResponseEntity.ok(this.voucherService.getPublishList());
	}
}
