package com.infosys.purchase.rewardpoints.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public class CustomerTransactionRequest {

	@NotNull
	@Positive
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
