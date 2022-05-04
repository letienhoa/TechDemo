package com.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TableRequest {

	@JsonProperty("status")
	private int status;
	
	@JsonProperty("floor")
	private int floor;
	
	public TableRequest() {
		
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int statusa) {
		this.status = statusa;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	@JsonProperty("createdBy")
	private Integer createdBy;
	
	@JsonProperty("updatedBy")
	private Integer updatedBy;

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
