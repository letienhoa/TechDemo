package com.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRequest {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("previousPoint")
	private Integer previousPoint;
	
	@JsonProperty("point")
	private Integer point;
	
	@JsonProperty("createdBy")
	private Integer createdBy;
	
	@JsonProperty("updatedBy")
	private Integer updatedBy;
	
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
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