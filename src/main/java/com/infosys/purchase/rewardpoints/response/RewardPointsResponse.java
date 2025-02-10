package com.infosys.purchase.rewardpoints.response;

import java.time.LocalDate;

public class RewardPointsResponse {

	private Integer id;
	private Integer customerId;
	private Integer month;
	private Integer year;
	private LocalDate date;
	private Integer points;
	private Long sum;
	
	public RewardPointsResponse(Integer customerId, Integer month, Integer year, Long sum) {
		super();
		this.customerId = customerId;
		this.month = month;
		this.year = year;
		this.sum = sum;
	}

	public RewardPointsResponse(Integer id, 
			Integer customerId, 
			Integer month, 
			Integer year, 
			LocalDate date, 
			Integer points) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.month = month;
		this.year = year;
		this.date = date;
		this.points = points;
	}
	
	public RewardPointsResponse(Integer customerId, 
			Integer month, 
			Integer year, 
			Integer points) {
		super();
		this.customerId = customerId;
		this.month = month;
		this.year = year;
		this.points = points;
	}

	public RewardPointsResponse(LocalDate date, Integer points) {
		this.date = date;
		this.points = points;
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
	
	public Integer getMonth() {
		return month;
	}
	
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getPoints() {
		return points;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
	}

	public Long getSum() {
		return sum;
	}

	public void setSum(Long sum) {
		this.sum = sum;
	}
	
	
}
