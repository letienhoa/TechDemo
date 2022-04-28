package com.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.order.request.VoucherRequest;

@Entity
@Table(name="voucher")
public class Voucher extends BaseEntity  {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	
	@Column(name = "name")
	public String name;

	@Column(name = "foodId")
	public Integer foodId;
	
	@Column(name = "value")
	public Integer value;
	
	@Column(name = "publishedDate")
	public Date publishedDate;
	
	@Column(name = "endDate")
	public Date endDate;
	
	public Voucher(VoucherRequest request) {
		this.name = request.name;
		this.foodId = request.foodId;
		this.value = request.value;
		this.publishedDate = request.publishedDate;
		this.endDate = request.endDate;
	}
	
	public Voucher() {}
}
