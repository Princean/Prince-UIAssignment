package com.infosys.purchase.rewardpoints.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RewardPointsRequest {

	@Positive
	private Integer customerId;
	
	@NotNull
	@Positive
	private Integer month;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
