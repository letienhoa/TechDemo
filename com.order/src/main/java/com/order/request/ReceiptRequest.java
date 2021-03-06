package com.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceiptRequest {
	
	@JsonProperty("id")
	private int id;

	@JsonProperty("employeeId")
	private int employeeId;

	@JsonProperty("customerId")
	private int customerId;

	@JsonProperty("totalPrice")
	private int totalPrice;

	@JsonProperty("typePayment")
	private int typePayment;

	@JsonProperty("typeService")
	private int typeService;

	@JsonProperty("tableId")
	private Integer tableId;

	@JsonProperty("receiptDetail")
	private String receiptDetail;
	
	@JsonProperty("customerPay")
	private int customerPay;
	
	@JsonProperty("voucherName")
	private String voucherName;
	
	@JsonProperty("discountPrice")
	private int discountPrice;
	
	@JsonProperty("createdBy")
	private Integer createdBy;
	
	@JsonProperty("updatedBy")
	private Integer updatedBy;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployee() {
		return employeeId;
	}

	public void setEmployee(int employee) {
		this.employeeId = employee;
	}

	public int getCustomer() {
		return customerId;
	}

	public void setCustomer(int customerId) {
		this.customerId = customerId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(int typePayment) {
		this.typePayment = typePayment;
	}

	public int getTypeService() {
		return typeService;
	}

	public void setTypeService(int typeService) {
		this.typeService = typeService;
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTable(Integer tableId) {
		this.tableId = tableId;
	}

	public String getReceiptDetail() {
		return receiptDetail;
	}

	public void setReceiptDetail(String receiptDetail) {
		this.receiptDetail = receiptDetail;
	}

	public int getCustomerPay() {
		return customerPay;
	}

	public void setCustomerPay(int customerPay) {
		this.customerPay = customerPay;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

}