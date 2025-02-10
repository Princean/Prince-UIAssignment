package com.infosys.purchase.rewardpoints.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.purchase.rewardpoints.request.RewardPointsRequest;
import com.infosys.purchase.rewardpoints.response.RewardPointsResponse;
import com.infosys.purchase.rewardpoints.service.RewardPointsService;
import com.infosys.purchase.rewardpoints.util.ResponseUtil;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/rewardpoints")
public class RewardPointsController {

	private static final Logger log = LoggerFactory.getLogger(RewardPointsController.class);
	
	@Autowired
	ResponseUtil responseUtil;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired 
	public RewardPointsService rewardPointsService;
	
	/**
	  * Get reward points by id.
	  *
	  * @param rewardPointsRequest the request containing the reward points id
	  * @return a ResponseEntity containing the reward points or an error message
	  */
	@GetMapping("/get")
	public ResponseEntity<?> get(@Valid @RequestBody RewardPointsRequest rewardPointsRequest) {
		
		log.info("Reward points controller get - starts");
		List<RewardPointsResponse> RewardPointsResponse;
		try {
			RewardPointsResponse = rewardPointsService.get(rewardPointsRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while fetching reward points", e);
			return responseUtil
					.createErrorResponse(messageSource.getMessage("rewardpoints.get.failed", null, Locale.getDefault()), null);
		}
		log.info("Reward points controller get - ends");
		return responseUtil
				.createResponse(null, RewardPointsResponse);
	}
	
	/**
	  * Get all reward points.
	  *
	  * @param rewardPointsRequest the request containing the criteria for fetching all reward points
	  * @return a ResponseEntity containing all reward points or an error message
	  */
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(@Valid @RequestBody RewardPointsRequest rewardPointsRequest) {
		
		log.info("Reward points controller get all - starts");
		List<RewardPointsResponse> RewardPointsResponse;
		try {
			RewardPointsResponse = rewardPointsService.getAll(rewardPointsRequest);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while fetching all reward points", e);
			return responseUtil
					.createErrorResponse(messageSource.getMessage("rewardpoints.get.failed", null, Locale.getDefault()), null);
		}
		log.info("Reward points controller get all - ends");
		return responseUtil
				.createResponse(null, RewardPointsResponse);
	}
}
