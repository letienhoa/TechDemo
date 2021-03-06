package com.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.order.request.MaterialRequest;
@Entity
@Table(name="material")
public class Material extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name" )
	private String name;
	@Column(name = "type")
	private int type;
	@Column(name = "quality")
	private String quality;
	@Column(name = "amountTotal")
	private Integer amount;
	
	public Material() {

	}
	public Material(MaterialRequest re, int i) {
		this.id = re.getId();
		this.name = re.getName();
		this.type =re.getType();
		this.quality=re.getQuality();
		this.amount = re.getAmount();
		this.setUpdatedAt(new Date());
		this.updateBy =re.getUpdatedBy();
	}
	public Material(MaterialRequest re) {
		this.name = re.getName();
		this.type =re.getType();
		this.quality=re.getQuality();
		this.updateBy =re.getUpdatedBy();
		this.createdBy= re.getCreatedBy();
		if(re.getAmount()!=null) {
			this.amount = re.getAmount();	
		}else this.amount=0;
		
	}
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
