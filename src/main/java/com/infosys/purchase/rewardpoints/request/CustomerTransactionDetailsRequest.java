package com.infosys.purchase.rewardpoints.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CustomerTransactionDetailsRequest {

	@Positive
	private Integer id;
	
	@NotNull
	@Positive
	private Integer customerId;
	
	@NotBlank
	private String spentDetails;
	
	@NotNull
	@Positive
	private Double amount;
	
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
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
