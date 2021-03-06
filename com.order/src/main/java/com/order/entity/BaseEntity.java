package com.order.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdTime")
	@CreationTimestamp
	private Date createdAt;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updatedTime")
	@UpdateTimestamp
	private Date updatedAt;
	
	@Column(name="createdBy")
	protected int createdBy;
	
	@Column(name="updateBy")
	protected int updateBy;
	
	public String amountToCurrencyString(float amountTotal, String currency) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(amountTotal) + currency;
	}
	
	public String amountToCurrencyString(float amountTotal) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(amountTotal) + "đ";
	}
	
	public String getDateFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}
	
	public String getDatetimeFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
	}
	
	public String getDateFormatVNEmptyIfNull(Date date) {
		if(date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}
	
	public String getDatetimeFormatVNEmptyIfNull(Date date) {
		if(date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
	}
	
	public String getMonthYearFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("MM/yyyy").format(date));
		}
	}
	
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getCreatedAtFormatVN() {
		return this.getDatetimeFormatVN(createdAt);
	}
	
	public String getUpdatedAtFormatVN() {
		return this.getDatetimeFormatVN(updatedAt);
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	
}
