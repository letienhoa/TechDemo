package com.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.order.request.TableRequest;

@Entity
@Table(name = "tables")
public class Tables extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "status")
	private int status;
	@Column(name = "floor")
	private int floor;

	public Tables() {

	}

	public Tables(TableRequest re, int i) {
		this.floor = re.getFloor();
		this.status = re.getStatus();
		this.setUpdatedAt(new Date());
	}

	public Tables(TableRequest re) {
		this.floor = re.getFloor();
		this.status = re.getStatus();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int isStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

}
