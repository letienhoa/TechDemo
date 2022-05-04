package com.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "materialId")
	private int material;

	@Column(name = "drinkCakeId")
	private int drinkCake;

	@Column(name = "amountForOne")
	private Double AmountForOne;
	
	@Column(name = "size")
	private String size;

	public Recipe() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaterial() {
		return material;
	}

	public void setMaterial(int material) {
		this.material = material;
	}

	public int getDrinkCake() {
		return drinkCake;
	}

	public void setDrinkCake(int drinkCake) {
		this.drinkCake = drinkCake;
	}

	public Double getAmountForOne() {
		return AmountForOne;
	}

	public void setAmountForOne(Double amountForOne) {
		AmountForOne = amountForOne;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
