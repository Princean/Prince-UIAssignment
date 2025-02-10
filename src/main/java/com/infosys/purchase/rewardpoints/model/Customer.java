package com.infosys.purchase.rewardpoints.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeq")
    @SequenceGenerator(name = "customerSeq", sequenceName = "customer_seq", allocationSize = 1)
	private Integer id; 
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="email")
	private String email;
	
	@Column(name="status", nullable=false)
	private Boolean status;
	
	@Column(name="created_by", nullable=false)
	private Integer createdBy;
	
	@Column(name="created_date", nullable=false)
	private LocalDate createdDate;
	
	@Column(name="modified_by")
	private Integer modifiedBy;
	
	@Column(name="modified_date")
	private LocalDate modifiedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	
}
