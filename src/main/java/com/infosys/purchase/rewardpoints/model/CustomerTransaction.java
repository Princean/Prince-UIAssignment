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
@Table(name="customer_transaction")
public class CustomerTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerTransactionSeq")
    @SequenceGenerator(name = "customerTransactionSeq", sequenceName = "customer_transaction_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="customer_id", nullable=false)
	private Integer customerId;
	
	@Column(name="spent_details", nullable=false)
	private String spentDetails;
	
	@Column(name="amount", nullable=false)
	private Double amount;
	
	@Column(name="date", nullable=false)
	private LocalDate date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getSpentDetails() {
		return spentDetails;
	}

	public void setSpentDetails(String spentDetails) {
		this.spentDetails = spentDetails;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
