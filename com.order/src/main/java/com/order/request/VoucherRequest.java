package com.order.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoucherRequest {
	@JsonProperty("id")
	public int id;
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("foodId")
	public Integer foodId;
	
	@JsonProperty("value")
	public Integer value;
	
	@JsonProperty("publishedDate")
	public Date publishedDate;
	
	@JsonProperty("endDate")
	public Date endDate;
	
	@JsonProperty("createdBy")
	public int createdBy;
	
	@JsonProperty("updatedBy")
	public int updatedBy;
}
