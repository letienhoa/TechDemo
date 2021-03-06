package com.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.order.request.ReceiptRequest;

@Entity
@Table(name="receipt")
public class Receipt extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId")
	private Customer customer;
	@Column(name = "totalPrice")
	private int totalPrice;
	@Column(name = "typePayment")
	private int typePayment;
	@Column(name = "typeService")
	private int typeService;
	@Column(name = "tableId")
	private Integer tableId;
	@Column(name = "receiptDetail")
	private String receiptDetail;
	
	@Column(name = "customerPay")
	private Integer customerPay;
	@Column(name = "voucherName")
	private String voucherName;
	@Column(name = "discountPrice")
	private Integer discountPrice;
	
	public Receipt() {
		
	}
	public Receipt(ReceiptRequest re, int i,Employee em,Customer cus) {
		this.id = re.getId();
		this.employee= em;
		this.customer = cus;
		this.totalPrice =re.getTotalPrice();
		this.typePayment = re.getTypePayment();
		this.typeService = re.getTypeService();
		this.tableId = re.getTableId();
		this.receiptDetail = re.getReceiptDetail();
		this.setUpdatedAt(new Date());
		
		this.customerPay =re.getCustomerPay();
		this.voucherName = re.getVoucherName();
		this.discountPrice=re.getDiscountPrice();
		this.updateBy =re.getUpdatedBy();
	}
	public Receipt(ReceiptRequest re, Employee em,Customer cus) {
		this.employee= em;
		this.customer = cus;
		this.totalPrice =re.getTotalPrice();
		this.typePayment = re.getTypePayment();
		this.typeService = re.getTypeService();
		this.tableId = re.getTableId();
		this.receiptDetail = re.getReceiptDetail();
		
		this.customerPay =re.getCustomerPay();
		this.voucherName = re.getVoucherName();
		this.discountPrice=re.getDiscountPrice();
		this.updateBy =re.getUpdatedBy();
		this.createdBy= re.getCreatedBy();
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public Integer getCustomerPay() {
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
	public Integer getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}
	
		
}
