package com.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeRequest {

	@JsonProperty("materialId")
	private int materialId;

	@JsonProperty("foodId")
	private int foodId;

	@JsonProperty("amountForOne")
	private Double amountForOne;

	@JsonProperty("size")
	private String size;

	@JsonProperty("createdBy")
	private Integer createdBy;
	
	@JsonProperty("updatedBy")
	private Integer updatedBy;
	
	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public Double getAmountForOne() {
		return amountForOne;
	}

	public void setAmountForOne(Double amountForOne) {
		this.amountForOne = amountForOne;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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
