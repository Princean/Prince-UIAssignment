package com.infosys.purchase.rewardpoints.repo;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infosys.purchase.rewardpoints.model.RewardPoints;
import com.infosys.purchase.rewardpoints.response.RewardPointsResponse;

@Repository
public interface RewardPointsRepo extends JpaRepository<RewardPoints, Integer> {

	/**
     * Retrieves reward points based on customer ID and date range.
     *
     * @param customerId the ID of the customer (can be null to fetch for all customers)
     * @param dateRange the date range to filter reward points
     * @return a list of RewardPointsResponse containing the reward points details
     */
	@Query("SELECT new com.infosys.purchase.rewardpoints.response.RewardPointsResponse(r.customerId, r.rewardMonth, r.rewardYear, sum(r.points)) "
			+ "FROM RewardPoints r "
			+ "WHERE r.customerId = case when :customerId IS null then r.customerId else :customerId end "
			+ "AND FUNCTION('PARSEDATETIME', CONCAT(r.rewardYear,'-', LPAD(CAST(r.rewardMonth AS STRING), 2, '0'),'-','31'), 'yyyy-MM-dd') >= :dateRange "
			+ "GROUP BY r.customerId, r.rewardMonth, r.rewardYear")
	List<RewardPointsResponse> getRewardPoints(@Param("customerId") Integer customerId,
			@Param("dateRange") LocalDate dateRange);
}
