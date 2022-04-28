package com.order.request;

public class Pagination {
	public int page = 1;
	public int take = 10;
	public String query;
	public String sortField;
	public String order;
	
	public Pagination(int page, int take, String query, String sortField, String order) {
		this.page = page;
		this.take = take;
		this.query = query;
		this.sortField = sortField;
		this.order = order;
	}
}
