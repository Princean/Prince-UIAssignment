package com.infosys.purchase.rewardpoints.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="reward_points")
public class RewardPoints {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rewardPointsSeq")
    @SequenceGenerator(name = "rewardPointsSeq", sequenceName = "reward_points_Seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="customer_id", nullable=false)
	private Integer customerId;
	
	@Column(name="reward_month", nullable=false)
	private Integer rewardMonth;
	
	@Column(name="reward_year", nullable=false)
	private Integer rewardYear;
	
	@Column(name="points", nullable=false)
	private Integer points;

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

	public Integer getRewardMonth() {
		return rewardMonth;
	}

	public void setRewardMonth(Integer rewardMonth) {
		this.rewardMonth = rewardMonth;
	}

	public Integer getRewardYear() {
		return rewardYear;
	}

	public void setRewardYear(Integer rewardYear) {
		this.rewardYear = rewardYear;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
