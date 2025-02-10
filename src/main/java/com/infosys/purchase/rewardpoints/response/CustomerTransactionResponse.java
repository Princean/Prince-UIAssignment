package com.infosys.purchase.rewardpoints.response;

import java.time.LocalDate;

public class CustomerTransactionResponse {

	private Integer id;
	private Integer customerId;
	private String spentDetails;
	private Double amount;
	private LocalDate date;
	
	public CustomerTransactionResponse(Integer id, 
			Integer customerId, 
			String spentDetails, 
			Double amount, 
			LocalDate date) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.spentDetails = spentDetails;
		this.amount = amount;
		this.date = date;
	}

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
