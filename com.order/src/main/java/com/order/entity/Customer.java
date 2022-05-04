package com.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.order.request.CustomerRequest;

@Entity
@Table(name="customer")
public class Customer extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "phone")
	private String phone;
	@Column(name = "name")
	private String name;
	@Column(name = "previousPoint")
	private Integer previousPoint;
	@Column(name = "point")
	private Integer point;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPreviousPoint() {
		return previousPoint;
	}
	public void setPreviousPoint(Integer previousPoint) {
		this.previousPoint = previousPoint;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Customer() {
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer(CustomerRequest customer) {
		this.phone = customer.getPhone();
		this.name = customer.getName();
		this.previousPoint = customer.getPreviousPoint();
		this.point = customer.getPoint();
		this.updateBy =customer.getUpdatedBy();
		this.createdBy =customer.getCreatedBy();
	}
}
