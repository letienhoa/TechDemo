package com.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.order.request.DrinkCakeRequest;

@Entity
@Table(name="drinkcake")
public class DrinkCake extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private Integer price;
	@Column(name = "picture")
	private byte[] picture;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public DrinkCake() {
		
	}
	
	public DrinkCake(DrinkCakeRequest request) {
		this.id = request.getId();
		this.name = request.getName();
		this.price = request.getPrice();
		this.updateBy =request.getUpdatedBy();
		this.createdBy =request.getCreatedBy();
	}
	
}
