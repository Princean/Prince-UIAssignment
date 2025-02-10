package com.infosys.purchase.rewardpoints.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RewardPointsUtil {

	private static final Logger log = LoggerFactory.getLogger(RewardPointsUtil.class);
	
	private static final Integer REWARD_SLAB_1 = 50;
	private static final Integer REWARD_SLAB_2 = 100;
	
	 /**
	  * Calculate reward points based on the amount.
	  * 
	  * The method calculates reward points using two slabs:
	  * - For amounts greater than REWARD_SLAB_2, the points are calculated as:
	  *   (amount - REWARD_SLAB_2) * 2 + REWARD_SLAB_1
	  * - For amounts greater than REWARD_SLAB_1 but less than or equal to REWARD_SLAB_2,
	  *   the points are calculated as: amount - REWARD_SLAB_1
	  * 
	  * @param amount the amount spent by the customer
	  * @return the calculated reward points
	  */
	public Integer calculateRewardPoints(Double amount) {
		
		log.info("Reward points util calculate reward points - starts");
		Integer rewardPoints = 0;
		try {
			log.debug("Amount: ", amount);
			if (amount > REWARD_SLAB_2) {
				rewardPoints = (amount.intValue() - REWARD_SLAB_2) * 2 + REWARD_SLAB_1;
			} else if (amount > REWARD_SLAB_1) {
				rewardPoints = amount.intValue() - REWARD_SLAB_1;
			}
			log.debug("Reward points: ", rewardPoints);
		} catch (Exception e) {
			log.error("Error occurred while calculating reward points", e);
			throw e;
		}
		log.info("Reward points util calculate reward points - ends");
		return rewardPoints;
	}
	
}
