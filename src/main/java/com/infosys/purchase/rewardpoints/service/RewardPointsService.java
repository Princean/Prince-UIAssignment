package com.infosys.purchase.rewardpoints.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.infosys.purchase.rewardpoints.events.CustomerTransactionEvent;
import com.infosys.purchase.rewardpoints.model.CustomerTransaction;
import com.infosys.purchase.rewardpoints.model.RewardPoints;
import com.infosys.purchase.rewardpoints.repo.RewardPointsRepo;
import com.infosys.purchase.rewardpoints.request.RewardPointsRequest;
import com.infosys.purchase.rewardpoints.response.RewardPointsResponse;
import com.infosys.purchase.rewardpoints.util.RewardPointsUtil;

@Service
public class RewardPointsService {

	private static final Logger log = LoggerFactory.getLogger(RewardPointsService.class);
	
	@Autowired
	private RewardPointsUtil rewardPointsUtil;
	
	@Autowired
	private RewardPointsRepo rewardPointsRepo;
	
	/**
	  * Handles the event of saving reward points when a customer transaction occurs.
	  *
	  * @param event the customer transaction event
	  */
	@Async
    @EventListener
    public void rewardPointSaveEvent(CustomerTransactionEvent event) {
		
		log.info("Reward points save event - starts");
        try {
        	
        	//LocalDate currentDate = LocalDate.now();
        	RewardPoints rewardPoints = new RewardPoints();
        	
        	rewardPoints.setCustomerId(((CustomerTransaction) event.getSource()).getCustomerId());
        	rewardPoints.setRewardMonth(((CustomerTransaction) event.getSource()).getDate().getMonthValue());
        	rewardPoints.setRewardYear(((CustomerTransaction) event.getSource()).getDate().getYear());
        	rewardPoints.setPoints(rewardPointsUtil.calculateRewardPoints(event.getAmount()));
        	
        	rewardPointsRepo.save(rewardPoints);
        	
        } catch (Exception e) {
        	log.error("Error while saving reward points", e);
        	throw e;
        }
        log.info("Reward points save event - ends");
    }

	/**
	  * Retrieves all reward points based on the given request.
	  *
	  * @param rewardPointsRequest the request containing the criteria for fetching all reward points
	  * @return a list of reward points responses
	  */
	public List<RewardPointsResponse> getAll(RewardPointsRequest rewardPointsRequest) {
		
		log.info("Get all reward points - starts");
		List<RewardPointsResponse> rewardPointsList = null;
		try {
			LocalDate period = LocalDate.now().minusMonths(rewardPointsRequest.getMonth());
			rewardPointsList = rewardPointsRepo
					.getRewardPoints(null, period);
        } catch (Exception e) {
        	log.error("Error while getting reward points", e);
            throw e;
        }
		log.info("Get all reward points - ends");
		return rewardPointsList;
	}

	/**
	  * Retrieves reward points based on the given request.
	  *
	  * @param rewardPointsRequest the request containing the criteria for fetching reward points
	  * @return a list of reward points responses
	  */
	public List<RewardPointsResponse> get(RewardPointsRequest rewardPointsRequest) {
		
		log.info("Get reward points - starts");
		List<RewardPointsResponse> rewardPointsList = null;
		try {
			LocalDate period = LocalDate.now().minusMonths(rewardPointsRequest.getMonth());
			rewardPointsList = rewardPointsRepo
					.getRewardPoints(rewardPointsRequest.getCustomerId(), period);
            
        } catch (Exception e) {
        	log.error("Error while getting reward points", e);
            throw e;
        }
		log.info("Get reward points - ends");
		return rewardPointsList;
	}
}
